<template>
  <div id="app">
    <b-navbar toggleable="md" type="dark" variant="info">
      <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>
      <b-navbar-brand>RMS</b-navbar-brand>
      <b-collapse is-nav id="nav_collapse" v-if="user">
        <b-navbar-nav>
          <router-link :to="{ name: 'User', params: {id: user.id} }" tag="b-nav-item">User</router-link>


          <b-nav-item-dropdown text="Households" v-if="households && households.length">
            <div v-for="household in households">
              <router-link :to="{ name: 'Household', params: {id: household.id} }" tag="b-dropdown-item">
                {{ household.street + " " + household.buildingNumber }}
              </router-link>
            </div>
          </b-nav-item-dropdown>

          <!--<b-nav-item>{{lists}}</b-nav-item>-->

          <b-nav-item-dropdown text="Shopping Lists" v-if="lists && lists.length">
            <div v-for="list in lists">
              <router-link :to="{ name: 'ShoppingList', params: {id: list.id}}" tag="b-dropdown-item">
                {{ list.name }}
              </router-link>
            </div>
          </b-nav-item-dropdown>
        </b-navbar-nav>
        <b-navbar-nav></b-navbar-nav>
      </b-collapse>
      <b-collapse is-nav id="nav_collapse" v-else>
        <b-navbar-nav class="ml-auto">
          <b-nav-item href="/user">Register</b-nav-item>
          <b-form inline>
            <b-form-input id="email" type="email" required placeholder="user@email.com"></b-form-input>
            <b-form-input id="password" type="password" required></b-form-input>
            <b-button type="submit" variant="primary">Login</b-button>
          </b-form>
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
          state: "Czechia",
          lists: [{
            id: 1,
            name: "Zakladne potreby"
          }]
        }, {
          id: 2,
          street: "Dlha",
          buildingNumber: "135",
          zipCode: "11 000",
          state: "Slovakia"
        }],
        lists: []
      };
    },
    created() {
      let lists = [];
      this.households.forEach(h => {
        if (h.lists) {
          h.lists.forEach(l => lists.push(l))
        }
      });
      this.lists = lists
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
