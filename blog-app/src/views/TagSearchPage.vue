<template>
    <div>
        <my-nav></my-nav>
        <br><br><br>
        <h1>{{ this.Tag.tagName }}</h1>
        <h3>相关文章数量: {{ this.Tag.articleCount }}</h3>
        <my-art-list :key="articleKey" :articles="this.Articles" :users="this.users"></my-art-list>
    </div>
</template>

<script>
export default {
    data() {
        return {
            Tag: {},
            Articles: [],
            users: {},
            articleKey: 0
        }
    },
    async created() {
        await this.fetchTag();
        await this.fetchArticles();
    },
    methods: {
        async fetchTag() {
            this.$http.get(`/tag/detail/${this.$route.params.id}`)
                .then(response => {
                    if (response.data.code == 0) {
                        this.Tag = response.data.data;
                    } else {
                        this.$message.error('获取标签失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('获取标签失败：' + error);
                });
        },
        async fetchArticles() {
            this.$http.get(`/tag/${this.$route.params.id}/articles`)
                .then(response => {
                    if (response.data.code == 0) {
                        this.Articles = response.data.data;
                        this.Articles.forEach(article => {
                            if (!this.users[article.authorId]) {
                                this.users[article.authorId] = '';
                            }
                        });
                    } else {
                        this.$message.error('获取文章失败：' + response.data.msg);
                    }
                    this.fetchNames(Object.keys(this.users));
                })
                .catch(error => {
                    this.$message.error('获取文章失败：' + error);
                });
        },
        async fetchNames(keys) {
            if (keys.length == 0) {return;}
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

<style>
h1,h3 {
    text-align: center;
    color: aliceblue;
}
</style>