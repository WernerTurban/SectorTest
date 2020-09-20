<template>
  <v-app>
    <UserForm />
  </v-app>
</template>

<script>
import UserForm from './components/UserForm';

export default {
  name: 'App',

  components: {
    UserForm
  },

  data: () => ({})
};
</script>
