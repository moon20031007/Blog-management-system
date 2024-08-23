<template> 
    <div class="shell">
       
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="60px" class="demo-ruleForm">
            <div>
                <div id="welcome-lines" style="margin-top: -40px; margin-right: -50px;">
                    <div id="w-line-1">你好! </div>
                    <div id="w-line-2">欢迎回来</div>
                </div>
                <div class="form-container">
                    <el-form-item label="用户名" prop="id">
                        <el-input v-model.number="ruleForm.id"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="pass">
                        <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                    </el-form-item>
                </div>
            </div>
            <el-form-item style="text-align: center; margin-left: -10%;">
                    <el-button type="primary" @click="submitForm('ruleForm')" style="margin-right: 20px; padding: 12px 40px; border-radius: 50px; font-size: 16px;">登陆</el-button>
                    <el-button @click="resetForm('ruleForm')" style="padding: 12px 40px; border-radius: 50px; font-size: 16px;">重置</el-button>
                <div class="link-container">
                    <router-link to="/" class="small-font-link" style="text-decoration: none; color: grey">返回首页</router-link>
                    <router-link to="/signup" class="small-font-link" style="text-decoration: none; color: grey">注册</router-link>
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
                            if (response.data.code == 0) {
                                this.$message.success('登录成功！');
                                this.$router.push('/');
                            } else {
                                this.$message.error('登录失败：' + response.data.msg);
                            }
                        })
                        .catch(error => {
                            this.$message.error('登录失败：' + error);
                        });
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
.shell {
    border: 1px solid #dcdfe6;
    padding: 30px;
    border-radius: 20px;
    width: 30%;
    margin: 10% auto 0;
    background-color: white;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 增加阴影 */
}

.form-container {
  justify-content: center; /* 水平居中 */
  margin-top: 20px;
}

.small-font-link {
    font-size: 14px;
    color: grey;
    text-decoration: none;
}


.link-container {
    display: flex;
    justify-content: center;
    margin-left: -10%;
    gap: 80px;
    margin-top: 10px;
}

#welcome-lines {
  width: 100%;
  text-align: center;
  line-height: 1;
}
 
#w-line-1 {
  color: #7f7f7f;
  font-size: 65px;
}
 
#w-line-2 {
  color: #9c9c9c;
  font-size: 30px;
  margin-top: 17px;
}


</style>