<template>
  <div class="login">
    <div class="login-wrapper">
      <div class="title">Vote System</div>
      <LoginForm :method="userLogin" v-on:login="login"></LoginForm>
      <button v-on:click="manage">管理投票選項</button>
    </div>
  </div>
</template>

<script>
import LoginForm from '@/components/LoginForm.vue'
export default {
  name: 'LoginComponent',
  components: {
    LoginForm
  },
  data() {
    return {
      userLogin: {
        title: 'Login to Vote',
        type: 'userLogin'
      },
      managerLogin: {
        title: 'Manager Login',
        type: 'managerLogin'
      }
    }
  },
  methods: {
    async login({username, password}) {
      try {
        const {status} = await fetch(`${process.env.VUE_APP_BACKEND_URL}/api/login`, {
          method: 'POST',
          body: JSON.stringify({
            name: username,
            password: password
          }),
          headers: {
            'Content-Type': 'application/json'
          }
        })
        if(status === 200) {
          localStorage.setItem('username', username);
          this.$router.push('/vote');
        } else {
          alert('輸入內容錯誤，請重新輸入');
        }
      } catch(err){
        console.log(err);
      }
    },
    manage(){
      this.$router.push('/manage');
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">

.login {
  width: 100%;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
.title {
  font-size: 64px;
  font-weight:900;
  color: #416586;
  margin-bottom: 20px;

  @media screen and (max-width: 768px) {
    font-size: 36px;
  }
}

button {
  height: 30px;
  color:#416586;
  background-color: #fff;
  border-radius: 5px;
  border: none;
  box-shadow: 0px 0px 4px rgba(0, 0, 0, 0.2);
  margin-bottom: 2px;

  &:hover{
    cursor: pointer;
  box-shadow: 0.5px 0.5px 4px rgba(0, 0, 0, 0.2);
  }
}
</style>
