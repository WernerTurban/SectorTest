<template>
  <v-container>
    <v-row>
      <v-col cols="12" align="center">
        <h2>
          Please enter your name and pick the Sectors you are currently involved
          in.
        </h2>
      </v-col>
    </v-row>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-row align="center">
        <v-col cols="12">
          <v-text-field
            v-model="name"
            :counter="26"
            label="Name"
            :rules="nameRules"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="6">
          <v-card-text>
            <v-treeview
              v-model="selectedSectors"
              :items="items"
              selected-color="indigo"
              open-on-click
              selectable
              return-object
              expand-icon="mdi-chevron-down"
              on-icon="mdi-bookmark"
              off-icon="mdi-bookmark-outline"
              indeterminate-icon="mdi-bookmark-minus"
            ></v-treeview>
          </v-card-text>
        </v-col>
        <v-col cols="12" sm="6">
          <v-card-text>
            <div
              v-if="selectedSectors.length === 0"
              key="title"
              class="title font-weight-light grey--text pa-4 text-center"
            >
              Select sectors
            </div>

            <v-scroll-x-transition group hide-on-leave>
              <v-chip
                v-for="(selection, i) in selectedSectors"
                :key="i"
                color="grey"
                dark
                small
                class="ma-1"
              >
                <v-icon left small>mdi-checkbox-marked-circle</v-icon>
                {{ selection.name }}
              </v-chip>
            </v-scroll-x-transition>
          </v-card-text>
        </v-col>
        <v-col cols="6" justify="end" align="end">
          <v-checkbox
            class="checkbox"
            :rules="[v => !!v || 'You must agree to continue!']"
            label="Agree to terms"
            required
            align-end
          ></v-checkbox>
        </v-col>

        <v-col cols="6" align="end">
          <v-btn
            :disabled="!valid"
            color="success"
            class="mr-4"
            @click="validate"
            >Save</v-btn
          >
        </v-col>
      </v-row>
    </v-form>
    <v-snackbar
      v-model="showSectorInvalidMessage"
      :timeout="3000"
      top
      color="red darken-1"
    >
      <span>Atleast one sector needs to be selected</span>
      <v-btn text color="white" @click="showSectorInvalidMessage = false"
        >Close</v-btn
      >
    </v-snackbar>
  </v-container>
</template>

<script>
import SectorService from '../service/SectorService';

export default {
  data() {
    return {
      name: '',
      user: {
        id: null,
        name: '',
        sectors: []
      },
      items: [],
      selectedSectors: [],
      nameRules: [
        v => v.length >= 2 || 'Name must have atleast 2 letters',
        v => (v && v.length <= 26) || 'Name must be less than 26 characters',
        v => (v || '').indexOf(' ') < 0 || 'No spaces are allowed'
      ],
      valid: true,
      showSectorInvalidMessage: false
    };
  },
  methods: {
    validate() {
      if (this.selectedSectors.length == 0) {
        this.showSectorInvalidMessage = true;
      }
      if (this.$refs.form.validate() && this.selectedSectors.length > 0) {
        this.user.name = this.name;
        this.user.sectors = this.selectedSectors;
        SectorService.saveUser(this.user).then(response => {
          this.user = response.data;
          sessionStorage.setItem('user', JSON.stringify(this.user));
        });
      }
    },
    checkUserData() {
      if (sessionStorage.user != null || sessionStorage.user != undefined) {
        const user = JSON.parse(sessionStorage.user);
        this.user = user;
        this.name = user.name;
        this.selectedSectors = user.sectors;
      }
    }
  },
  created() {
    SectorService.getAllSectors().then(response => {
      this.items = response.data;
      this.checkUserData();
    });
  }
};
</script>

<style lang="scss" scoped>
p {
  margin-bottom: 0px !important;
}
.container {
  margin-top: 10%;
  @media only screen and (min-width: 1920px) {
    width: 1000px;
  }
}
.checkbox {
  width: 200px;
}
.mr-4 {
  @media only screen and (min-width: 1920px) {
    margin-bottom: 20px;
  }
  margin-bottom: 10px;
}
h2 {
  margin-bottom: 5%;
}
</style>
