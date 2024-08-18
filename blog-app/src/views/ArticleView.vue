<template>
    <div>
        <my-nav></my-nav>
        <el-container>
            <el-aside width="300px"><my-asi-list></my-asi-list></el-aside>
            <el-main>
                <article>
                    <h2>{{ article.title }}</h2>
                    <el-card class="custom-card">
                        <div class="card-content">
                            <i class="el-icon-user-solid"></i>{{ users[article.authorId] }}
                            <i class="el-icon-upload"></i>{{ $formatTime(article.publishTime) }}
                            <i class="el-icon-view"></i>{{ article.readCount }}
                            <i class="el-icon-star-off"></i>{{ article.likeCount }}
                            <i class="el-icon-chat-line-round"></i>{{ article.commentCount }}
                        </div>
                        <div>
                            文章标签:
                            <div v-for="tag in tags" :key="tag.tagId" class="tag">
                                <el-link :href="`/tag/${tag.tagId}`"><el-tag>{{ tag.tagName }}</el-tag></el-link>
                            </div>
                        </div>
                    </el-card>
                    <p>{{ article.content }}</p>
                </article>
                <el-card v-bind:key="commentsKey">
                    <h3>评论区</h3>
                    <el-form :model="commentForm">
                        <el-input v-model="commentForm.content" type="textarea" autosize placeholder="请输入评论" style="width: 85%;"></el-input>
                        <el-button type="primary" @click="submitComment()">提交</el-button>
                    </el-form>
                    <div v-for="comment in comments" :key="comment">
                        <i class="el-icon-user-solid user">{{ users[comment.commenterId] }}</i><br>
                        {{ comment.content }}<br>
                        <small>
                            <i class="el-icon-time"></i>{{ $formatTime(comment.commentTime) }}
                            <i class="el-icon-star-off"></i>{{ comment.likeCount }}
                        </small>
                        <el-button type="text" @click="showReplyView(comment.commentId, 0, 0)"><i class="el-icon-chat-line-square">回复</i></el-button>
                        <el-collapse v-model="activeNames">
                            <el-collapse-item v-if="findReplies(comment.commentId).length" :title="activeNames.includes(comment.commentId) ? '收起' : '展开'" :name=comment.commentId>
                                <div v-for="reply in findReplies(comment.commentId)" :key="reply">
                                    <i class="el-icon-user-solid user">{{ users[reply.replierId] }}：</i>
                                    <template v-if="reply.replyType == 1">
                                        <span class="user">@{{ users[reply.toId] }}</span>
                                    </template>
                                    {{ reply.content }}<br>
                                    <i class="el-icon-time"></i>{{ $formatTime(reply.replyTime) }}
                                    <i class="el-icon-star-off"></i>{{ reply.likeCount }}
                                    <el-button type="text" @click="showReplyView(comment.commentId, 1, reply.replyId, reply.replierId)"><i class="el-icon-chat-line-square">回复</i></el-button><br>
                                </div>
                            </el-collapse-item>
                            <el-form v-show="isReplyFormVisible.includes(comment.commentId)" ref="replyForm" :model="replyForm">
                                <el-input v-model="replyForm.content" type="textarea" autosize :placeholder="replyForm.toReplyId ? `回复 @${users[replyForm.toId]}` : '回复'" style="width: 85%;"></el-input>
                                <el-button type="primary" @click="submitReply()">提交</el-button>
                            </el-form>
                        </el-collapse>
                    </div>
                </el-card>
            </el-main>
        </el-container>
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
            replyForm: { content: '', replyType: '', commentId: '', toReplyId: '', toId: ''},
            activeNames: [],
            users: {}
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
                    console.log(response.data);
                    this.$message.success('提交成功');
                    this.commentForm.content = '';
                    this.commentsKey++;
                })
                .catch(error => {
                    console.error('提交文章失败:', error);
                    this.$message.error('提交文章失败');
                });
        },
        submitReply() {
            if (this.replyForm.content == '') {
                this.$message.error('请输入回复内容');
                return;
            }
            this.$http.post(`/reply/add`, this.replyForm)
                .then(response => {
                    console.log(response.data);
                    this.$message.success('提交成功');
                    this.replyForm.content = '';
                    this.commentsKey++;
                })
                .catch(error => {
                    console.error('提交回复失败:', error);
                    this.$message.error('提交回复失败');
                });
        },
        async fetchArticle() {
            this.$http.get(`/article/detail/${this.$route.params.id}`)
                .then(response => {
                    this.article = response.data.data;
                    if (!this.users[this.article.authorId]) {
                        this.users[this.article.authorId] = '';
                    }
                })
                .catch(error => {
                    console.error('获取文章失败:', error);
                    this.$message.error('获取文章失败');
                });
        },
        async fetchTags() {
            this.$http.get(`/tag/article/${this.$route.params.id}`)
                .then(response => {
                    this.tags = response.data.data;
                })
                .catch(error => {
                    console.error('获取标签失败:', error);
                    this.$message.error('获取标签失败');
                });
        },
        async fetchComments() {
            this.$http.get(`/comment/list/${this.$route.params.id}`)
                .then(response => {
                    this.comments = response.data.data;
                    this.comments.forEach(comment => {
                        if (!this.users[comment.commenterId]) {
                            this.users[comment.commenterId] = '';
                        }
                    });
                })
                .catch(error => {
                    console.error('获取评论失败:', error);
                    this.$message.error('获取评论失败');
                });
        },
        async fetchReplies() {
            this.$http.get(`/reply/list/${this.$route.params.id}`)
                .then(response => {
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
                    });
                    this.fetchNames(Object.keys(this.users));
                })
                .catch(error => {
                    console.error('获取回复失败:', error);
                    this.$message.error('获取回复失败');
                });
        },
        fetchNames(keys) {
            console.log(keys.join(','));
            this.$http.get(`/user/names`, {params: {userIds: keys.join(',')}})
                .then(response => {
                    response.data.data.forEach(user => this.users[user.accountId.toString()] = user.nickname);
                    this.commentsKey++;
                })
                .catch(error => {
                    console.log(error);
                    this.$message.error('获取用户名失败');
                });
        }
    }
}
</script>

<style>
.user {
    color: slateblue;
}

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
</style>