<template>
  <div class="signup">
    <h1 style="margin-bottom: 50px; margin-top: 100px;">Sign Up</h1>
    <b-form  @submit.stop.prevent>
      <div style="margin-bottom: 50px;">
        <label for="email" style="margin-bottom: 10px; font-size: 20px;">email</label>
        <b-form-input v-model="email" id="email" style="width: 30%; margin-left: 35%;"></b-form-input>
      </div>
      <div style="margin-bottom: 50px;">
        <label for="name" style="margin-bottom: 10px; font-size: 20px;">name</label>
        <b-form-input v-model="name" id="name" style="width: 30%; margin-left: 35%;"></b-form-input>
      </div>
      <div style="margin-bottom: 50px;">
        <label for="password" style="margin-bottom: 10px; font-size: 20px;">password</label>
        <b-form-input v-model="password" id="password" type="password" style="width: 30%; margin-left: 35%;"></b-form-input>
      </div>
    </b-form>
    <b-button variant="outline-primary" @click="submitForm">Sign Up</b-button>
  </div>
</template>

<script>
import {signUp} from "@/api";

export default {
  name: "SignUp",
  data() {
    return {
      email: '',
      name: '',
      password: ''
    }
  },
  methods:{
    submitForm(){
      const userData = {
        email: this.email,
        name: this.name,
        password: this.password
      };

      signUp(userData)
          .then((res) => {
            console.log(res);
          })
          .catch((err) => {
            console.log(err);
          });
    }
  }
}
</script>

<style scoped>

</style>