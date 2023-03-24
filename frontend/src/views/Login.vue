<template>
  <div class="login">
    <h1 style="margin-bottom: 50px; margin-top: 100px;">Login</h1>
    <b-form  @submit.stop.prevent>
      <div style="margin-bottom: 50px;">
        <label for="email" style="margin-bottom: 10px; font-size: 20px;">email</label>
        <b-form-input v-model="username" id="email" style="width: 30%; margin-left: 35%;"></b-form-input>
      </div>
      <div style="margin-bottom: 50px;">
        <label for="password" style="margin-bottom: 10px; font-size: 20px;">password</label>
        <b-form-input v-model="password" id="password" type="password" style="width: 30%; margin-left: 35%;"></b-form-input>
      </div>
    </b-form>
    <b-button variant="outline-primary" @click="submitForm">Login</b-button>
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

  }
}
</script>

<style scoped>

</style>