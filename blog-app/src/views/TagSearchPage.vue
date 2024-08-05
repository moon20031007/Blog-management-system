<template>
    <div>
        <my-nav></my-nav>
        <h1>{{ this.Tag.tagName }}</h1>
        <h2>{{ this.Tag.articleCount }}</h2>
        <my-art-list></my-art-list>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            Tag: {}
        }
    },
    async created() {
        await this.fetchTag();
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
        }
    }
}
</script>

<style>
h1 {
    text-align: center;
}
</style>