<template>
  <div class="login-form">
    <div class="title">{{method.title}}</div>
    <div class="form">
      <div class="input-wrapper username-input">
        <div class="label">username</div>
        <input type="text" placeholder="username" v-model="username" />
      </div>
      <div class="input-wrapper password-input">
        <div class="label">password</div>
        <input type="password" placeholder="password" v-model="password"/>
      </div>
      <button class="submit" v-on:click="login">Login</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UserLogin',
  props: {
    method: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    login() {
      const { username, password } = this;
      const validPattern = /[/w]*/;
      if(!validPattern.test(username) || !validPattern.test(password)) {
        alert('輸入格式錯誤，請重新輸入');
        return;
      }
      this.$emit('login', { username: this.username, password: this.password });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.login-form {
  position: relative;
  text-align: start;
  padding: 25px 0;
}

.title {
  font-size: 20px;
  font-weight: 700;
  color: #416586;
  margin-bottom: 10px;
}

.form {
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  gap: 24px;
}

.input-wrapper {
  .label {
    font-size: 14px;
    font-weight: 500;
    color: #416586;
    margin-bottom: 5px;
  }

  input {
    height: 30px;
    border-radius: 5px;
    border: 1px solid #D9D9D9;
    padding-left: 5px;
    outline: none;

    &:focus {
      border: 2px solid #b6b6b6;
    }
  }
}

button.submit {
  width: 67px;
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
