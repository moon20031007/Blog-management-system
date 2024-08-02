<template>
    <div>
        <my-nav></my-nav>
        <h1>{{ getTagName(id) }}</h1>
        <my-art-list></my-art-list>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    computed: {
        id() {
            return this.$route.params.id
        }
    },
    data() {
        return {
            Tags: [
                { id: 1, name: 'Vue.js' },
                { id: 2, name: 'JavaScript' },
                { id: 3, name: 'Web Development' },
                { id: 4, name: 'Frontend' },
                { id: 5, name: 'CSS' }
            ]
        }
    },
    async created() {
        await this.fetchTag();
    },
    methods: {
        async fetchTag() {
            axios.get(`/tag/detail/${this.id}`)
                .then(response => {
                    this.Tags = response.data.data;
                })
                .catch(error => {
                    console.error('获取标签失败:', error);
                    this.$message.error('获取标签失败');
                });
        },
        getTagName(tagId) {
            const tagItem = this.Tags.find(item => item.id === tagId);
            return tagItem ? tagItem.name : 'Name not found';
        }
    }
}
</script>

<style>
h1 {
    text-align: center;
}
</style>