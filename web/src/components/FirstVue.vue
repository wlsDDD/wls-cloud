<template>
<div>
  <HelloWorld msg="hhh"/>
  <h1>{{ msg }}</h1>
</div>
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue';

export default {
  name: "FirstVue",
  components: {
    HelloWorld
  },
  props: {
    msg: String
  }
}
</script>

<style scoped>

</style>