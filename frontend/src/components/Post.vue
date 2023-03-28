<template>
  <b-card
      :title="item.title"
      :img-src="item.imageUrl"
      img-alt="Image"
      img-top
      tag="article"
      style="width: 15rem;"
      class="mb-2"
  >
    <b-card-text>
      {{ item.content }}
    </b-card-text>
    <b-card-text>{{ item.createdAt }}</b-card-text>
  </b-card>
</template>

<script>
export default {
  name: "Post",
  props: {
    item: Object
  }
}
</script>

<style scoped>

</style>