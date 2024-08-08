<template>
    <div>
        <my-nav></my-nav>
        <el-card>
            <el-row>
                <el-col :span="12"><div><h2>{{ account.nick_name }}</h2></div></el-col>
                <el-col :span="12"><div style="text-align: right;">
                    <el-button round>编辑资料</el-button>
                    <el-button round>管理博文</el-button>
                    <el-button round>退出登陆</el-button>
                </div></el-col>
            </el-row>
            <el-row>
                <el-col :span="8"><div class="grid-content bg-purple">用户名: {{ account.user_name }}</div></el-col>
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
export default {
    data() {
        return{
            account: {
                user_id: "1",
                user_name: "gh1007",
                pass_word: "12345",
                nick_name: "Anson",
                gender: "男",
                birthday: "2003-10-7",
                phone: "18318806888",
                address: "深圳",
                email: "1220644940@qq.com"
            }
        }
    }
}
</script>

<style>

</style>
