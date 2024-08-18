<template>
    <div style="border: 1px solid #dcdfe6; padding: 30px; border-radius: 4px; width: 30%; margin: 10% auto 0;">
        <div class="botton-container"><el-button plain><router-link to="/">回到首页</router-link></el-button></div>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

            <div class="login-container">
                <el-form-item label="用户名" prop="id">
                    <el-input v-model.number="ruleForm.id"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="pass">
                    <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                </el-form-item>
            </div>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
                <div class="link-container"><router-link to="/signup" class="small-font-link">还没有账号？去注册</router-link>
                </div>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
    data() {
        var validateId = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入用户名'));
            } else {
                callback();
            }
        };
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                callback();
            }
        };
        return {
            ruleForm: {
                id: '',
                pass: ''
            },
            rules: {
                id: [
                    { validator: validateId, trigger: 'blur' }
                ],
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                const form = { username: this.ruleForm.id, password: this.ruleForm.pass };
                if (valid) {
                    this.$http.post('/login', form)
                        .then(response => {
                            console.log(response);
                            this.$message({
                                message: '登录成功！',
                                type: 'success'
                            });
                            this.$router.push('/');
                        })
                        .catch(error => {
                            console.error('登录失败:', error);
                            this.$message.error('登录失败，请检查用户名和密码');
                        });
                    let allCookies = document.cookie;
                    console.log(allCookies);
                } else {
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
}
</script>

<style>
.login-container {
    position: relative;
}

.small-font-link {
    float: right;
    font-size: 15px;
}

.botton-container {
    text-align: left;
    margin-bottom: 20px;
}

.link-container {
    text-align: right;
}
</style>