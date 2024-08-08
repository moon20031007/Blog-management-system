<template>
    <div>
        <my-nav></my-nav>
        <el-card>
            <el-row>
                <el-col :span="12"><div><h2>{{ account.nickname }}</h2></div></el-col>
                <el-col :span="12"><div style="text-align: right;">
                    <el-button round>编辑资料</el-button>
                    <el-button round>管理博文</el-button>
                    <el-button round>退出登陆</el-button>
                </div></el-col>
            </el-row>
            <el-row>
                <el-col :span="8"><div class="grid-content bg-purple">用户名: {{ account.username }}</div></el-col>
                <el-col :span="8"><div class="grid-content bg-purple-light">性别: {{ account.gender }}</div></el-col>
                <el-col :span="8"><div class="grid-content bg-purple">生日: {{ account.birthday }}</div></el-col>
            </el-row>
            <el-row>
                <el-col :span="8"><div class="grid-content bg-purple">电话: {{ account.phone }}</div></el-col>
                <el-col :span="8"><div class="grid-content bg-purple-light">住址: {{ account.address }}</div></el-col>
                <el-col :span="8"><div class="grid-content bg-purple">邮箱: {{ account.email }}</div></el-col>
            </el-row>
        </el-card>
        <my-art-list></my-art-list>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return{
            account: {}
        }
    },
    async created() {
        await this.fetchAccount();
    },
    methods: {
        async fetchAccount() {
            axios.get(`http://localhost:7000/user/detail/${this.$route.params.id}`)
                .then(response => {
                    this.account = response.data.data;
                })
                .catch(error => {
                    console.error('获取账户失败:', error);
                    this.$message.error('获取账户失败');
                });
        }
    }
}
</script>

<style>

</style>
