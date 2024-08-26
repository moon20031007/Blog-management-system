<template>
    <div>
        <my-nav></my-nav>
        <br>
        <br>
        <el-container>
            <el-aside width="300px"><my-asi-list></my-asi-list></el-aside>
            <el-main>
                <article>
                    <el-card class="custom-card">
                        <h2>{{ article.title }}</h2>
                        <div class="card-content">
                            <img :src="require('@/assets/images/user.png')" class="user-image">{{ users[article.authorId] }}&nbsp;
                            <img :src="require('@/assets/images/time.png')" class="time-view-image">{{ $formatTime(article.publishTime) }}&nbsp;
                            <img :src="require('@/assets/images/view.png')" class="time-view-image">{{ article.readCount }}&nbsp;
                            <i class="el-icon-chat-line-round"></i>{{ article.commentCount }}&nbsp;
                            <div class="like" @click="putLike('Article', article.articleId)">
                                <img v-if="likeCount.article" :src="require('@/assets/images/liked.png')" alt="Liked" class="like-image">
                                <img v-else :src="require('@/assets/images/like.png')" alt="Like" class="like-image">
                                {{ article.likeCount }}
                            </div>
                        </div>
                        <div>
                            文章标签:
                            <div v-for="tag in tags" :key="tag.tagId" class="tag">
                                <el-link :href="`/tag/${tag.tagId}`"><el-tag>{{ tag.tagName }}</el-tag></el-link>
                            </div>
                        </div>
                        <p class="content">{{ article.content }}</p>
                    </el-card>
                </article>
                <br>
                <el-card :key="commentsKey">
                    <h3 class="h3-1">评论区</h3>
                    <el-form :model="commentForm">
                        <el-input v-model="commentForm.content" type="textarea" autosize placeholder="请输入评论"
                            style="width: 85%;"></el-input>
                        <el-button type="primary" @click="submitComment()">提交</el-button>
                    </el-form>
                    <div v-for="comment in comments" :key="comment">
                        <img :src="require('@/assets/images/user.png')" class="user-image">{{ users[comment.commenterId] }}<br>
                        <div class="content" style="margin-left: 15px;">{{ comment.content }}</div>
                        <small>
                            <img :src="require('@/assets/images/time.png')" class="time-view-image">{{ $formatTime(comment.commentTime) }}&nbsp;
                            <div class="like" @click="putLike('Comment', comment.commentId)" style="margin-right: 0px;">
                                <img v-if="likeCount.comment[comment.commentId]" :src="require('@/assets/images/liked.png')" class="like-image">
                                <img v-else :src="require('@/assets/images/like.png')" class="like-image">
                                {{ comment.likeCount }}
                            </div>
                            <el-button type="text" @click="showReplyView(comment.commentId, 0, 0)">
                                <i class="el-icon-chat-line-square">回复</i>
                            </el-button>
                        </small>
                        <el-collapse v-model="activeNames">
                            <el-collapse-item v-if="findReplies(comment.commentId).length"
                                :title="activeNames.includes(comment.commentId) ? '收起' : '展开'" :name=comment.commentId>
                                <div class="content" v-for="reply in findReplies(comment.commentId)" :key="reply">
                                    <img :src="require('@/assets/images/user.png')" class="user-image">{{ users[reply.replierId] }}: 
                                    <template v-if="reply.replyType == 1">
                                        <span class="user">@{{ users[reply.toId] }} </span>
                                    </template>
                                    <i style="font-style: normal;">{{ reply.content }}</i>
                                    <br>
                                    <img :src="require('@/assets/images/time.png')" class="time-view-image">{{ $formatTime(reply.replyTime) }}&nbsp;
                                    <div class="like" @click="putLike('Reply', reply.replyId)">
                                        <img v-if="likeCount.reply[reply.replyId]" :src="require('@/assets/images/liked.png')" class="like-image">
                                        <img v-else :src="require('@/assets/images/like.png')" class="like-image">
                                        {{ reply.likeCount }}
                                    </div>&nbsp;
                                    <el-button type="text" @click="showReplyView(comment.commentId, 1, reply.replyId, reply.replierId)"><i class="el-icon-chat-line-square">回复</i></el-button>
                                    <br>
                                    <br>
                                </div>
                            </el-collapse-item>
                            <el-form v-show="isReplyFormVisible.includes(comment.commentId)" ref="replyForm"
                                :model="replyForm">
                                <el-input v-model="replyForm.content" type="textarea" autosize
                                    :placeholder="replyForm.toReplyId ? `回复 @${users[replyForm.toId]}` : '回复'"
                                    style="width: 85%;"></el-input>
                                <el-button type="primary" @click="submitReply()">提交</el-button>
                            </el-form>
                        </el-collapse>
                    </div>
                </el-card>
            </el-main>
        </el-container>
        <el-backtop></el-backtop>
    </div>
</template>


<script>
export default {
    data() {
        return {
            article: {},
            commentsKey: 0,
            isReplyFormVisible: [],
            tags: [],
            comments: [],
            replies: [],
            commentForm: { articleId: this.$route.params.id, content: '' },
            replyForm: { content: '', replyType: '', commentId: '', toReplyId: '', toId: '' },
            activeNames: [],
            users: {},
            likeCount: {
                article: false,
                comment: {},
                reply: {}
            }
        };
    },
    async created() {
        await this.fetchArticle();
        await this.fetchTags();
        await this.fetchComments();
        await this.fetchReplies();
    },
    methods: {
        findReplies(id) {
            return this.replies.filter(reply => reply.commentId === id);
        },
        showReplyView(commentId, type, replyId, toId) {
            if (this.isReplyFormVisible.includes(commentId) && this.replyForm.toReplyId == replyId) {
                this.isReplyFormVisible = [];
            } else {
                this.isReplyFormVisible = [];
                this.isReplyFormVisible.push(commentId);
            }
            this.replyForm.replyType = type;
            this.replyForm.commentId = commentId;
            this.replyForm.toReplyId = (replyId === 0) ? '' : replyId;
            this.replyForm.toId = (toId === 0) ? '' : toId;
        },
        submitComment() {
            if (this.commentForm.content == '') {
                this.$message.error('请输入评论内容');
                return;
            }
            this.$http.post(`/comment/add`, this.commentForm)
                .then(response => {
                    if (response.data.code == 0) {
                        this.$message.success('提交成功');
                        this.commentForm.content = '';
                        setTimeout(() => location.reload(), 3000);
                    } else {
                        this.$message.error('提交失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('提交文章失败：' + error);
                });
        },
        submitReply() {
            if (this.replyForm.content == '') {
                this.$message.error('请输入回复内容');
                return;
            }
            this.$http.post(`/reply/add`, this.replyForm)
                .then(response => {
                    if (response.data.code == 0) {
                        this.$message.success('提交成功');
                        this.replyForm.content = '';
                        setTimeout(() => location.reload(), 3000);
                    } else {
                        this.$message.error('提交回复失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('提交回复失败：' + error);
                });
        },
        putLike(type, id) {
            this.$http.put(`/like/${type}/${id}`)
                .then(response => {
                    if (response.data.code == 0) {
                        if (type == 'Article') {
                            if (this.likeCount.article == false) {
                                this.article.likeCount++;
                            } else {
                                this.article.likeCount--;
                            }
                            this.likeCount.article = !this.likeCount.article;
                        } else if (type == 'Comment') {
                            let comment = this.comments.find(c => c.commentId == id);
                            if (this.likeCount.comment[id] == false) {
                                comment.likeCount++;
                            } else {
                                comment.likeCount--;
                            }
                            this.likeCount.comment[id] = !this.likeCount.comment[id];
                        } else if (type == 'Reply') {
                            let reply = this.replies.find(r => r.replyId == id);
                            if (this.likeCount.reply[id] == false) {
                                reply.likeCount++;
                            } else {
                                reply.likeCount--;
                            }
                            this.likeCount.reply[id] = !this.likeCount.reply[id];
                        }
                        this.$message.success('点赞成功！');
                        this.$forceUpdate();
                    } else {
                        this.$message.error('点赞失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('点赞失败：' + error);
                })
        },
        async fetchArticle() {
            this.$http.get(`/article/detail/${this.$route.params.id}`)
                .then(response => {
                    if (response.data.code == 0) {
                        this.article = response.data.data;
                        if (!this.users[this.article.authorId]) {
                            this.users[this.article.authorId] = '';
                        }
                    } else {
                        this.$message.error('获取文章失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('获取文章失败：' + error);
                });
        },
        async fetchTags() {
            this.$http.get(`/tag/article/${this.$route.params.id}`)
                .then(response => {
                    if (response.data.code == 0) {
                        this.tags = response.data.data;
                    } else {
                        this.$message.error('获取标签失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('获取标签失败：' + error);
                });
        },
        async fetchComments() {
            this.$http.get(`/comment/list/${this.$route.params.id}`)
                .then(response => {
                    if (response.data.code == 0) {
                        this.comments = response.data.data;
                        this.comments.forEach(comment => {
                            if (!this.users[comment.commenterId]) {
                                this.users[comment.commenterId] = '';
                            }
                            this.likeCount.comment[comment.commentId] = false;
                        });
                    } else {
                        this.$message.error('获取评论失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('获取评论失败：' + error);
                });
        },
        async fetchReplies() {
            this.$http.get(`/reply/list/${this.$route.params.id}`)
                .then(response => {
                    if (response.data.code == 0) {
                        this.replies = response.data.data;
                        this.replies.forEach(reply => {
                            if (!this.users[reply.replierId]) {
                                this.users[reply.replierId] = '';
                            }
                            if (reply.toReplyId) {
                                if (!this.users[reply.toId]) {
                                    this.users[reply.toId] = '';
                                }
                            }
                            this.likeCount.reply[reply.replyId] = false;
                        });
                    } else {
                        this.$message.error('获取回复失败：' + response.data.msg);
                    }
                    this.fetchNames(Object.keys(this.users));
                    this.fetchLikes();
                })
                .catch(error => {
                    this.$message.error('获取回复失败：' + error);
                });
        },
        async fetchNames(keys) {
            let userIdsParam = keys.length == 1 ? keys[0] : keys.join(',');
            this.$http.get(`/user/names`, { params: { userIds: userIdsParam } })
                .then(response => {
                    if (response.data.code == 0) {
                        response.data.data.forEach(user => this.users[user.accountId.toString()] = user.nickname);
                        this.commentsKey++;
                    } else {
                        this.$message.error('获取用户名失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('获取用户名失败：' + error);
                });
        },
        async fetchLikes() {
            this.$http.get(`/like/Article/${this.$route.params.id}`)
                .then(response => {
                    if (response.data.code == 0 && response.data.data == true) {
                        this.likeCount.article = true;
                    }
                })
                .catch(error => {
                    this.$message.error('获取点赞信息失败：' + error);
                });
            this.comments.forEach(comment => {
                this.$http.get(`/like/Comment/${comment.commentId}`)
                    .then(response => {
                        if (response.data.code == 0 && response.data.data == true) {
                            this.likeCount.comment[comment.commentId] = true;
                            this.commentsKey++;
                        }
                    })
                    .catch(error => {
                        this.$message.error('获取点赞信息失败：' + error);
                    });
            });
            this.replies.forEach(reply => {
                this.$http.get(`/like/Reply/${reply.replyId}`)
                    .then(response => {
                        if (response.data.code == 0 && response.data.data == true) {
                            this.likeCount.reply[reply.replyId] = true;
                        }
                    })
                    .catch(error => {
                        this.$message.error('获取点赞信息失败：' + error);
                    });
            });
        }
    }
}
</script>

<style>
.custom-card {
    padding: 0px;
}

.card-content {
    margin-bottom: 10px;
}

.tag {
    display: inline;
    margin-right: 5px;
}
.like {
    cursor: pointer;
    display: inline-block;
}
.h3-1 {
    background-color: #75a6e5;
    color: white;
    padding: 0.5rem 1rem;
    border-radius: 0.25rem;
}
.user-image{
    cursor: pointer;
    width: 1.7%; /* 调整宽度 */
    height: 1.7%; /* 调整高度 */
    margin-right: 0px; /* 调整与右边文本的间距 */
    margin-bottom: 2px; /* 向下移动图片 */
    vertical-align: middle; /* 确保图片与文本垂直对齐 */
}
.time-view-image {
    cursor: pointer;
    width: 1.7%; /* 调整宽度 */
    height: 1.7%; /* 调整高度 */
    margin-right: -2px; /* 调整与右边文本的间距 */
    margin-bottom: 2px; /* 向下移动图片 */
    vertical-align: middle; /* 确保图片与文本垂直对齐 */
}
.like-image {
    width: 40%; /* 调整宽度 */
    height: 40%; /* 调整高度 */
    margin-right: -6px; /* 调整与右边文本的间距 */
    margin-bottom: 3px; /* 向下移动图片 */
    vertical-align: middle; /* 确保图片与文本垂直对齐 */
}
</style>