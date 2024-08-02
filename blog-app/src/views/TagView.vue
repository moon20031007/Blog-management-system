<template>
    <div>
        <my-nav></my-nav>
        <el-container class="centered-container">
            <el-main>
                <el-row class="grid-row">
                    <el-col v-for="Tag in Tags" :key="Tag.id" :span="6">
                        <div @click="viewTag(Tag.id)" class="grid-item">{{ `${Tag.name}` }}</div>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            Tags: []
        }
    },
    async created() {
        await this.fetchTags();
    },
    methods: {
        async fetchTags() {
            axios.get('/tag/list')
                .then(response => {
                    this.Tags = response.data.data;
                })
                .catch(error => {
                    console.error('获取标签失败:', error);
                    this.$message.error('获取标签失败');
                });
        },
        viewTag(id) {
            this.$router.push({ name: 'Tag_id', params: { id } });
        }
    }
}
</script>

<style>
.centered-container {
    display: flex;
    justify-content: center;
    align-items: flex-start;
    height: 90vh;
    padding-top: 2%;
}

.grid-row {
    display: flex;
    flex-wrap: wrap;
    margin: -10px;
}

.el-col {
    padding: 10px;
}

.grid-item {
    background-color: #f0f0f0;
    border: 1px solid #ddd;
    padding: 20px;
    text-align: center;
    font-size: 16px;
    color: #333;
    border-radius: 4px;
    box-sizing: border-box;
    width: 100%;
    max-width: calc(100% - 20px);
}
</style>