<template>
    <div>
        <my-nav></my-nav>
        <h1>{{ this.Tag.tagName }}</h1>
        <h1>{{ this.Tag.articleCount }}</h1>
        <my-art-list :articles="this.Articles"></my-art-list>
    </div>
</template>

<script>
import axios from 'axios';

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
            axios.get(`/tag/detail/${this.$route.params.id}`)
                .then(response => {
                    this.Tag = response.data.data;
                })
                .catch(error => {
                    console.error('获取标签失败:', error);
                    this.$message.error('获取标签失败');
                });
        },
        async fetchArticles() {
            axios.get(`/tag/${this.$route.params.id}/articles`)
                .then(response => {
                    this.Articles = response.data.data;
                })
                .catch(error => {
                    console.error('获取标签失败:', error);
                    this.$message.error('获取标签失败');
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