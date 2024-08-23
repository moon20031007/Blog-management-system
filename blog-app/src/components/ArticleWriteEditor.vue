<template>
    <div class="write-card">
        <div class="header-buttons">
            <el-button plain size='small' class="back-home-button">
                <router-link to="/" style="text-decoration: none; color: black">回到首页</router-link>
            </el-button>
        </div>
        <el-form :model="messageForm" status-icon :rules="rules" ref="messageForm" class="demo-ruleForm">

            <el-form-item prop="title" class="input">
                <el-input class="title" type="text" placeholder="标题" v-model="messageForm.title" maxlength="15">
                </el-input>
            </el-form-item>

            <el-form-item prop="textarea" class="write-input">
                <el-input type="textarea" placeholder="请输入你的文章" :rows="20" v-model="messageForm.textarea"
                    maxlength="2000" show-word-limit>
                </el-input>
            </el-form-item>

            <el-form-item class="form-checkbox" prop="tag">
                <el-checkbox-group v-model="messageForm.tag" :min="1">
                    <el-checkbox v-for="city in cities" :label="city" :key="city" style="color: black;">{{ city.tagName }}</el-checkbox>
                </el-checkbox-group>
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
    name: "MyArticleWriteEditor",
    data() {
        var validateArea = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('留言不能为空'));
            } else {
                callback();
            }
        };
        var validateTag = (rule, value, callback) => {
            if (value.length === 0) {
                callback(new Error('至少选择一个标签'));
            } else {
                callback();
            }
        };
        return {
            messageForm: {
                title: '',
                tag: [],
                textarea: ''
            },
            rules: {
                title: [
                    { required: true, message: '请输入标题', trigger: 'blur' }
                ],
                tag: [
                    { validator: validateTag, trigger: 'change' }
                ],
                textarea: [
                    { validator: validateArea, trigger: 'blur' }
                ]
            },
            cities: []
        };
    },
    async created() {
        await this.fetchCities();
    },
    methods: {
        async fetchCities() {
            this.$http.get('/tag/list')
                .then(response => {
                    if (response.data.code == 0) {
                        this.cities = response.data.data;
                    } else {
                        this.$message.error('获取标签失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('获取标签失败：' + error);
                });
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                const form = {
                    article: {
                        title: this.messageForm.title,
                        content: this.messageForm.textarea
                    },
                    tagIDs: this.messageForm.tag.map(tag => tag.tagId)
                };
                console.log(form);
                if (valid) {
                    this.$http.post('/article/add', form)
                        .then(response => {
                            if (response.data.code == 0) {
                                this.$message.success('文章提交成功！');
                                this.$router.push('/');
                            } else {
                                this.$message.error('文章提交失败：' + response.data.msg);
                            }
                        })
                        .catch(error => {
                            this.$message.error('文章提交失败：' + error);
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
.write-card {
    display: flex;
    justify-content: center;
    position: relative;
}

.write-input {
    margin-bottom: 10px;
    margin-top: 1.5rem;
    width: 50rem;
}

.title {
    font-size: 25px;
    font-weight: bold;
}

.el-checkbox-group {
    display: flex;
    flex-direction: row;
}

.form-checkbox {
    display: flex;
    justify-content: left;
    margin: 0;
}

.form-buttons {
    display: flex;
    justify-content: flex-end;
    margin-top: 0;

}

.back-home-button {
    position: absolute;
    left: 0;
    top: 0;
    margin-right: 10px;
    margin-top: 10px;
}

.demo-ruleForm {
    margin-top: 40px;
}
</style>