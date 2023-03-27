<template>
  <div class="wrapper">
    <b-card bg-variant="light">
      <b-form-group
          label-cols-lg="3"
          label="Write Post"
          label-size="lg"
          label-class="font-weight-bold pt-0"
          class="mb-0"
      >
        <b-form-group
            label="Title :"
            label-for="title"
            label-cols-sm="2"
            label-align-sm="right"
        >
          <b-form-input id="title" v-model="title"></b-form-input>
        </b-form-group>

        <b-form-group
            label="Content :"
            label-for="content"
            label-cols-sm="2"
            label-align-sm="right"
        >
          <b-form-input id="content" v-model="content"></b-form-input>
        </b-form-group>

        <b-form-group
            label="Image :"
            label-for="image"
            label-cols-sm="2"
            label-align-sm="right"
        >
          <input type="file" @change="handleFile">
        </b-form-group>

      </b-form-group>
      <b-button pill variant="outline-secondary" style="margin-left: 85%;" @click="save">Save</b-button>
    </b-card>
  </div>
</template>

<script>
import {savePost} from "@/api";

export default {
  name: 'PostWrite',
  data() {
    return {
      title: '',
      content: '',
      file: ''
    }
  },
  methods: {
    handleFile(e) {
      this.file = e.target.files[0];
    },
    save() {
      const postData = {
        title : this.title,
        content : this.content
      }
      const formData = new FormData();
      formData.append('image',this.file);
      formData.append('requestDto',new Blob([JSON.stringify(postData)], {
        type: "application/json"
      }));

      savePost(formData)
          .then((res) => {
            console.log(res);
            this.$router.push("/");
          })
          .catch((err) => {
            console.log(err);
          });
    }
  }

}
</script>

<style scoped>
.wrapper {
  width: 50%;
  margin-left: 25%;
  margin-top: 100px;
}
#content {
  height: 300px;
}
</style>
