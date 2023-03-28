<template>
  <div class="top">
    <div class="img-wrapper">
      <img :src="post.imageUrl" alt="Image">
    </div>
    <div class="info-post">
      <span>{{post.title}}</span><br>
      <span>{{author.name}}</span><br>
      <span>{{post.heartNum}}</span><br>
      <span>{{post.createdAt}}</span>
    </div>
  </div>
  <div class="content-post">
    {{post.content}}
  </div>
</template>

<script>
import {getPostDetail} from "@/api";

export default {
  name: "PostDetail",
  data() {
    return {
      post: '',
      author: ''
    }
  },
  mounted() {
    const id = this.$route.params.id;
    getPostDetail(id)
        .then((res) => {
          console.log(res);
          this.post = res.data;
          this.author = res.data.author;
        })
        .catch((err) => {
          console.log(err);
        })
  }
}
</script>

<style scoped>
.top {
  width: 80%;
  margin-left: 10%;
}
.img-wrapper {
  border: 1px solid black;
  width: 48%;
  height: 300px;
  display: inline-block;
  margin-right: 4%;
  text-align: center;
}
.info-post {
  border: 1px solid black;
  width: 48%;
  height: 300px;
  display: inline-block;
}
.content-post {
  width: 80%;
  margin-left: 10%;
  margin-top: 30px;
}
</style>