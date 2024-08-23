<template>
    <div class="Lmessage-card">
        <el-form :model="messageForm" status-icon :rules="rules" ref="messageForm" class="demo-ruleForm">
            <el-form-item prop="textarea" class="input">
                <el-input type="textarea" placeholder="请输入你的留言" :rows="10" v-model="messageForm.textarea" maxlength="100" show-word-limit>
                </el-input>
            </el-form-item>
            <el-form-item class="form-buttons">
                <el-button type="primary" @click="submitForm('messageForm')" size='small'>提交</el-button>
                <el-button @click="resetForm('messageForm')" size='small'>重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>


<script>
export default {
    name: "MyLmessageEditor",
    data() {
        var valiteArea = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('留言不能为空'));
            } else {
                callback();
            }
        };
        return {
            messageForm: {
                textarea: ''
            },
            rules: {
                textarea: [
                    { validator: valiteArea, trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                const form = { commenterId: 1, context: this.messageForm.textarea };
                if (valid) {
                    this.$http.post('/lmessage/add', form)
                        .then(response => {
                            if (response.data.code == 0) {
                                this.$message.success('留言提交成功！');                            
                            } else {
                                this.$message.error('留言提交失败：' + response.data.msg);
                            }
                        })
                        .catch(error => {
                            this.$message.error('留言提交失败：' + error);
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
.Lmessage-card {
    background-color: #f8f9fa;
    border: 1px solid #ddd;
    padding: 1rem;
    border-radius: 0.5rem;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
    margin-left: 3rem;
    margin-right: 3rem;
}

.input {
    margin-bottom: 0.5rem;
    margin-top: 1.5rem;
}

.form-buttons {
    display: flex;
    justify-content: flex-end;
}
</style>