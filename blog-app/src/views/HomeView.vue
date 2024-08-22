<template>
    <div>
        <my-nav></my-nav>
        <el-container>
            <el-aside width="300px">
                <my-asi-list></my-asi-list>
            </el-aside>
            <el-main>
                <my-art-list :key="articleKey" :articles="this.articles" :users="this.users"></my-art-list>
            </el-main>
        </el-container>
    </div>
</template>

<script>
export default {
    data() {
        return {
            articles: {},
            users: {},
            articleKey: 0
        }
    },
    async created() {
        await this.fetchLatestArticles();
    },
    methods: {
        async fetchLatestArticles() {
            this.$http.get('/article/home')
                .then(response => {
                    if (response.data.code == 0) {
                        this.articles = response.data.data;
                        this.articles.forEach(article => {
                            if (!this.users[article.authorId]) {
                                this.users[article.authorId] = '';
                            }
                        });
                    } else {
                        this.$message.error('获取最新文章失败：' + response.data.msg);
                    }
                    this.fetchNames(Object.keys(this.users));
                })
                .catch(error => {
                    this.$message.error('获取最新文章失败：' + error);
                });
        },
        async fetchNames(keys) {
            let userIdsParam = keys.length == 1 ? keys[0] : keys.join(',');
            this.$http.get(`/user/names`, { params: { userIds: userIdsParam } })
                .then(response => {
                    if (response.data.code == 0) {
                        response.data.data.forEach(user => this.users[user.accountId.toString()] = user.nickname);
                        this.articleKey++;
                    } else {
                        this.$message.error('获取用户名失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('获取用户名失败：' + error);
                });
        }
    }
}
</script>

<style></style>