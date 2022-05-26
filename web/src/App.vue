<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <FirstVue msg="xxx"/>
    <one msg="one"/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue';
import FirstVue from "@/components/FirstVue.vue";
import One from './components/OneVue.vue';

export default {
  name: 'App',
  components: {
    FirstVue,
    HelloWorld,
    One
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
