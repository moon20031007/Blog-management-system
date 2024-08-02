<template>
    <el-container>
        <el-header>
            <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
                <el-menu-item index="1" style="margin-left: 13%;"><router-link to="/"><el-link>首页</el-link></router-link></el-menu-item>
                <el-menu-item index="2"><router-link to="/tag"><el-link>标签</el-link></router-link></el-menu-item>
                <el-menu-item index="3"><router-link to="/log"><el-link>日志</el-link></router-link></el-menu-item>
                <el-menu-item index="4"><router-link to="/lmessage"><el-link>留言板</el-link></router-link></el-menu-item>
                <el-menu-item index="5"><router-link to="/write" @click.><el-link icon="el-icon-edit">写文章</el-link></router-link></el-menu-item>
                <div style="text-align: right; margin-top: 10px; margin-right: 20px;">
                    <template v-if="!isLoggedIn">
                        <router-link to="/login" tag="span"><el-link :underline="false">登录</el-link></router-link>
                        <router-link to="/signup" tag="span"><el-link :underline="false" style="margin-left: 10px;">注册</el-link></router-link>
                    </template>
                    <template v-else>
                        <el-dropdown>
                            <span class="el-dropdown-link"><el-avatar icon="el-icon-user-solid"></el-avatar></span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item @click.native="handleLogout">退出登录</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </template>
                </div>
            </el-menu>
        </el-header>
    </el-container>
</template>

<script>
export default {
    name: 'MyNavigation',
    data() {
        return {
            isLoggedIn: false,
            activeIndex: ''
        };
    },
    computed: {
        computedActiveIndex: function() {
            const routes = {
                '/': '1',
                '/tag': '2',
                '/log': '3',
                '/lmessage': '4',
                '/write': '5'
            };
            return routes[this.$route.path];
        }
    },
    created() {
        this.activeIndex = this.computedActiveIndex;
    }
}
</script>

<style></style>