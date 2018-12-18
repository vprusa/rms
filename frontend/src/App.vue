<template>
  <div id="app">
    <b-navbar toggleable="md" type="dark" variant="info">
      <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>
      <b-navbar-brand>RMS</b-navbar-brand>
      <b-collapse is-nav id="nav_collapse" v-if="user">
        <b-navbar-nav>
          <b-nav-item>
            <router-link :to="{ name: 'User', params: {id: user.id} }" tag="nav-item">User</router-link>
          </b-nav-item>


          <b-nav-item-dropdown text="Households" v-if="households && households.length">
            <div v-for="household in households">
              <router-link :to="{ name: 'Household', params: {id: household.id} }" tag="b-dropdown-item">
                {{ household.street + " " + household.buildingNumber }}
              </router-link>
            </div>
            <!--<router-link tag="b-dropdown-item" :to="{name: 'Household', params: {id:household.id}}">-->
            <!--{{ household.street + " " + household.buildingNumber }}-->
            <!--</router-link>-->
          </b-nav-item-dropdown>


        </b-navbar-nav>
      </b-collapse>
      <b-collapse is-nav id="nav_collapse" v-else>
        <b-navbar-nav class="ml-auto">
          <b-nav-item href="/user">Login</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'app',
    data() {
      return {
        user: {
          id: 1,
          firstName: "Jano",
          lastName: "Mrkvicka",
          email: "janko.mrkvicka@email.com"
        },
        households: [{
          id: 1,
          street: "Kratka",
          buildingNumber: "12a",
          zipCode: "44 212",
          state: "Czechia"
        }, {
          id: 2,
          street: "Dlha",
          buildingNumber: "135",
          zipCode: "11 000",
          state: "Slovakia"
        }]
      };
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
</style>
