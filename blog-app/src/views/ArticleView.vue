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
                            <i class="el-icon-upload"></i>{{ article.publish_time }}  
                            <i class="el-icon-view"></i>{{ article.read_count }}  
                            <i class="el-icon-star-off"></i>{{ article.like_count }}  
                            <i class="el-icon-chat-line-round"></i>{{ article.comments_count }}
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
                        <el-button type="text" @click="dialogFormVisible = true"><i class="el-icon-chat-line-square"></i></el-button>
                        <el-collapse v-model="activeNames" @change="handleChange">
                            <el-collapse-item title="展开回复" name="1">
                                
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
    export default {
        data() {
            return {
                article: {
                    author: "Anson",
                    title: "什么时候下班",
                    content: "现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班现在就下班",
                    publish_time: "2004-8-9",
                    read_count: "2000000000",
                    like_count: "1000000",
                    comments_count: "10"
                },
                tags: ['打工人','求下班'],
                comments: [
                    {commenter: 'Anson', content: '快下班', comment_time: '2024-8-9', like_count: '10000'},
                    {commenter: 'Anson', content: '快快下班', comment_time: '2024-8-9', like_count: '10000'},
                    {commenter: 'Anson', content: '没事哒', comment_time: '2024-8-9', like_count: '10000'},
                    {commenter: 'Anson', content: '好好好', comment_time: '2024-8-9', like_count: '10000'},
                ],
                dialogFormVisible: false,
                form: {
                reply: ''
                },
                activeNames: ['1']
            };  
        },
        methods: {
            handleChange(val) {
                console.log(val);
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
</style>