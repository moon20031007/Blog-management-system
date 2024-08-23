<template>
    <div>
        <my-nav></my-nav>
        <br>
        <el-container class="centered-container" style="width: 80%; margin: 0 auto;">
            <el-main>
                <el-row class="grid-row">
                    <el-col v-for="Tag in Tags" :key="Tag.tagId" :span="6">
                        <div @click="viewTag(Tag.tagId)" class="grid-item">{{ `${Tag.tagName}` }}</div>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</template>

<script>
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
            this.$http.get('/tag/list')
                .then(response => {
                    if (response.data.code == 0) {
                        this.Tags = response.data.data;
                    } else {
                        this.$message.error('获取标签失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('获取标签失败：' + error);
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
    background-color: #f8f9fa;
    border: 1px solid #ddd;
    padding: 20px;
    text-align: center;
    font-size: 16px;
    color: #333;
    border-radius: 4px;
    box-sizing: border-box;
    width: 100%;
    max-width: calc(100% - 20px);
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
}

.grid-item:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}
</style>