<template>
  <div class="zhuce">
    <div class="zhuce-box">

      <i class="el-icon-circle-close close-zhuce" @click="closezhuce"></i>

      <el-form :label-position="labelPosition" label-width="80px" :model="formRegister" :rules="rules">

        <el-form-item label="用户名" class="zhucela" prop="username">
          <el-input v-model="formRegister.userName"></el-input>
        </el-form-item>

        <el-form-item label="密码" class="zhucela" prop="pwd">
          <el-input placeholder="请输入密码" v-model="formRegister.pwd" show-password></el-input>
        </el-form-item>


        <el-form-item label="电话号码" class="zhucela" prop="mobile">
          <el-input v-model="formRegister.mobile"></el-input>
        </el-form-item>
      </el-form>

      <el-button type="primary" class="zhuce-button" @click="zhuce">注册</el-button>
    </div>

  </div>
</template>

<script>
    export default {
        data () {
            return {
                labelPosition: 'top',
                formRegister: {
                    username: '',
                    pwd: '',
                    mobile: ''
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {max: 10, message: '不能大于10个字符', trigger: 'blur'}
                    ],
                    nickname: [
                        {required: true, message: '请输入昵称', trigger: 'blur'},
                        {max: 11, message: '不能大于11个字符', trigger: 'blur'}
                    ],
                    pwd: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {max: 16, message: '不能大于16个字符', trigger: 'blur'}
                    ]
                }
            }
        },
        components: {

        },
        methods: {

            closezhuce () {
                this.$router.replace({path:'/'})
            },
            zhuce () {
                this.$axios
                    .post('/user/register', {
                        userName: this.formRegister.userName,
                        pwd: this.formRegister.pwd,
                        mobile: this.formRegister.mobile
                    })
                    .then(res => {
                        console.log(res);
                        if (res.data.code === 1) {
                            this.$message('注册成功请登陆')
                        }
                        if (res.data.code === -40000){
                            this.$message(res.data.subMsg)
                        }
                    })
                    .catch(err => {

                    })
            }

        }
    }
</script>

<style>

  .zhuce-box{

    position: absolute;
    top: 40%;
    left: 50%;
    transform: translate(-50%,-50%);
    width: 400px;
    padding: 40px;
    background: rgba(0,0,0,.8);
    box-sizing: border-box;
    box-shadow: 0 15px 25px rgba(0,0,0,.5);
    border-radius: 10px;
  }
  .close-zhuce{
    position: absolute;
    right: 25px;
    top: 20px;
    font-size: 20px;
    color: #fff;
  }
  .el-form--label-top .el-form-item__label{
    color: #fff;
    padding: 0 0 2px;
  }
  .zhuce-button{
    width: 320px;
    margin-top: 10px;
  }
</style>
