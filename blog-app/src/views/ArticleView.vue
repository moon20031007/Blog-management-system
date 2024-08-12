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
                            <i class="el-icon-user-solid"></i>{{ article.author }}
                            <i class="el-icon-upload"></i>{{ article.publishTime }}
                            <i class="el-icon-view"></i>{{ article.readCount }}
                            <i class="el-icon-star-off"></i>{{ article.likeCount }}
                            <i class="el-icon-chat-line-round"></i>{{ article.commentCount }}
                        </div>
                        <div>
                            文章标签:
                            <div v-for="tag in tags" :key="tag" class="tag">
                                {{ tag }}
                            </div>
                        </div>
                    </el-card>
                    <p>{{ article.content }}</p>
                </article>
                <el-card>
                    <h3>评论区</h3>
                    <div v-for="comment in comments" :key="comment">
                        <i class="el-icon-user-solid"></i>{{ comment.commenter }}<br>
                        {{ comment.content }}<br>
                        {{ comment.comment_time }}
                        <i class="el-icon-star-off"></i>{{ comment.like_count }}
                        <el-button type="text" @click="dialogFormVisible = true"><i
                                class="el-icon-chat-line-square">回复</i></el-button>
                        <el-collapse v-model="activeNames" @change="handleChange">
                            <el-collapse-item title="展开回复" name="1">
                                <div v-for="reply in replies" :key="reply">

                                </div>
                            </el-collapse-item>
                        </el-collapse>
                        <el-dialog :visible.sync="dialogFormVisible">
                            <el-form :model="form">
                                <el-form-item label="回复">
                                    <el-input v-model="form.reply" autocomplete="off"></el-input>
                                </el-form-item>
                            </el-form>
                            <div slot="footer" class="dialog-footer">
                                <el-button @click="dialogFormVisible = false">取 消</el-button>
                                <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
                            </div>
                        </el-dialog>
                    </div>
                </el-card>
            </el-main>
        </el-container>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            article: {},
            tags: ['打工人', '求下班'],
            comments: [
                { commenter: 'Anson', content: '快下班', comment_time: '2024-8-9', like_count: '10000' },
                { commenter: 'Anson', content: '快快下班', comment_time: '2024-8-9', like_count: '10000' },
                { commenter: 'Anson', content: '没事哒', comment_time: '2024-8-9', like_count: '10000' },
                { commenter: 'Anson', content: '好好好', comment_time: '2024-8-9', like_count: '10000' },
            ],
            dialogFormVisible: false,
            form: {
                replies: []
            },
            activeNames: ['1']
        };
    },
    async created() {
        await this.fetchArticle();
        await this.fetchTags();
    },
    methods: {
        handleChange(val) {
            console.log(val);
        },
        async fetchArticle() {
            axios.get(`http://localhost:7000/article/detail/${this.$route.params.id}`)
                .then(response => {
                    this.article = response.data.data;
                })
                .catch(error => {
                    console.error('获取文章失败:', error);
                    this.$message.error('获取文章失败');
                });
        },
        async fetchTags() {
            axios.get(`http://localhost:7000/article/detail/${this.$route.params.id}`)
                .then(response => {
                    this.tags = response.data.data;
                    console.log('获取标签中');
                })
                .catch(error => {
                    console.error('获取标签失败:', error);
                    this.$message.error('获取标签失败');
                });
        },
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
</style>