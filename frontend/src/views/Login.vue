<template>
  <div class="login">
    <h1 >Login</h1>
    <b-form  @submit.stop.prevent>
      <div >
        <label for="email" >email</label>
        <b-form-input v-model="username" id="email"></b-form-input>
      </div>
      <div>
        <label for="password">password</label>
        <b-form-input v-model="password" id="password" type="password"></b-form-input>
      </div>
    </b-form>
    <b-button variant="outline-primary" @click="submitForm">Login</b-button>
  </div>
  <div class="oauth">
    <b-button variant="outline-primary" @:click="submitOAuth('google')">Google</b-button>
    <b-button variant="outline-primary" @:click="submitOAuth('kakao')">Kakao</b-button>
    <router-link to="/sign-up">회원가입</router-link>
  </div>
</template>

<script>
import {formLogin} from "@/api";

export default {
  name: "Login",
  data() {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    submitForm(){
      const userData = {
        username: this.username,
        password: this.password
      };

      formLogin(userData)
          .then((res) => {
            console.log(res);
          })
          .catch((err) => {
            console.log(err);
          });

    },
    submitOAuth(provider) {
      location.href = 'http://localhost:8080/oauth2/authorization/' + provider;
    }
  }
}
</script>

<style scoped>
.login {
  text-align: center;
  width: 60%;
  margin-left: 20%;
  margin-top: 150px;
}
h1 {
  margin-bottom: 50px;
}
#email {
  margin-top: 10px;
  margin-bottom: 30px;
  margin-left: 25%;
  width: 50%;
}
#password {
  margin-top: 10px;
  margin-bottom: 30px;
  margin-left: 25%;
  width: 50%;
}
.oauth {
  text-align: center;
  width: 60%;
  margin-top: 20px;
  margin-left: 20%;

}
</style>