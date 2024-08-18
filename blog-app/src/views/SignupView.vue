<template>
    <div style="border: 1px solid #dcdfe6; padding: 20px; border-radius: 4px; width: 50%; margin: 2% auto 0">
        <el-form :model="accountForm" :rules="rules" ref="accountForm" label-width="100px">
            <div class="botton-container"><el-button plain ><router-link to="/">回到首页</router-link></el-button></div>
            <el-form-item label="用户名" prop="id">
                <el-input v-model="accountForm.id"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="accountForm.password"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="check_password">
                <el-input type="password" v-model="accountForm.check_password"></el-input>
            </el-form-item>
            <el-form-item label="昵称" prop="name">
                <el-input v-model="accountForm.name"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
                <el-select v-model="accountForm.sex" placeholder="请选择性别">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="生日" required>
                <el-col :span="11">
                    <el-form-item prop="date">
                        <el-date-picker type="date" placeholder="选择日期" v-model="accountForm.date"
                            style="width: 100%;"></el-date-picker>
                    </el-form-item>
                </el-col>
            </el-form-item>
            <el-form-item label="电话" prop="phone">
                <el-input v-model="accountForm.phone"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="accountForm.email"></el-input>
            </el-form-item>
            <el-form-item label="地址" prop="address">
                <el-input type="textarea" v-model="accountForm.address"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('accountForm')">立即创建</el-button>
                <div class="link-container"><router-link to="/login" class="small-font-link" >已经有账号？去登陆</router-link></div>
            </el-form-item>
        </el-form>
        
    </div>
</template>

<script>
export default {
    name: 'TestComponent',
    data() {
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.accountForm.password) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            accountForm: {
                id: '',
                password: '',
                check_password: '',
                name: '',
                sex: '',
                date: '',
                phone: '',
                email: '',
                address: ''
            },
            rules: {
                id: [
                    { required: true, message: '请输入用户名', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ],
                check_password: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                name: [
                    { required: true, message: '请输入昵称', trigger: 'blur' }
                ],
                sex: [
                    { required: true, message: '请选择性别', trigger: 'change' }
                ],
                date: [
                    { type: 'date', required: true, message: '请选择生日日期', trigger: 'change' }
                ],
                phone: [
                    { required: true, message: '请输入电话号码', trigger: 'blur' }
                ],
                email: [
                    { required: true, message: '请输入邮箱', trigger: 'blur' }
                ],
                address: [
                    { required: true, message: '请输入地址', trigger: 'blur' }
                ],
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    const form = {
                        username: this.accountForm.id,
                        password: this.accountForm.password,
                        nickname: this.accountForm.name,
                        gender: this.accountForm.sex,
                        birthday: this.accountForm.date,
                        phone: this.accountForm.phone,
                        address: this.accountForm.address,
                        email: this.accountForm.email
                    };
                    this.$http.post('/register', form)
                        .then(response => {
                            console.log(response.data);
                            this.$message({
                                message: '注册成功！',
                                type: 'success'
                            });
                            this.$router.push('/login');
                        })
                        .catch(error => {
                            console.error('注册失败:', error);
                            this.$message.error('注册失败，请检查用户名和密码');
                        });
                } else {
                    return false;
                }
            });
        }
    }
}
</script>

<style>
.botton-container {
  text-align: left; /* 将链接对齐到右边 */
  margin-bottom: 20px; /* 与表单项之间的间距 */
}

.link-container {
  text-align: right; /* 将链接对齐到右边 */
  margin-bottom: 20px; /* 与表单项之间的间距 */
}</style>