<template>
    <div>
        <my-nav></my-nav>
        <h1>{{ this.Tag.tagName }}</h1>
        <h1>{{ this.Tag.articleCount }}</h1>
        <my-art-list :articles="this.Articles"></my-art-list>
    </div>
</template>

<script>
export default {
    data() {
        return {
            Tag: {},
            Articles: []
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
                    } else {
                        this.$message.error('获取标签失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('获取标签失败：' + error);
                });
        }
    }
}
</script>

<style>
h1 {
    text-align: center;
}
</style>