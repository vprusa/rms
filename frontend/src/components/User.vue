<template>
  <div class="mx-auto container">
    <b-card :title="user.firstName + ' ' + user.lastName"
            :sub-title="user.email">
    </b-card>
    <div>
      <b-card-group deck>
        <b-card header="Requested Items"
                header-tag="header">
          <div v-if="items && items.length">
            <b-table responsive :items="items" :fields="item_fields">
              <template slot="id" slot-scope="data">
                <b-button variant="success">Buy</b-button>
              </template>
              <template slot="list" slot-scope="data">
                <router-link :to="{ name: 'ShoppingList', params: { id: data.value.id}}">{{data.value.name}}
                </router-link>
              </template>
            </b-table>
          </div>
          <div v-else>
            <p class="card-text">You don't have any requested items.</p>
          </div>
        </b-card>
        <b-card header="Households"
                header-tag="header">
          <div v-if="households && households.length">
            <b-table stacked :items="households" :fields="household_fields">
              <template slot="id" slot-scope="data">
                <router-link :to="{ name: 'Household', params: {id: data.value} }">Check</router-link>
              </template>
            </b-table>
          </div>
          <div v-else>
            <p class="card-text">You did not joined any households yet.</p>
            <b-button href="#" variant="primary">Join Household</b-button>
          </div>
        </b-card>
      </b-card-group>
    </div>
  </div>
</template>

<script>
  export default {
    name: "User",
    data() {
      return {
        user: {
          id: 1,
          firstName: "Jano",
          lastName: "Mrkvicka",
          email: "janko.mrkvicka@email.com"
        },
        item_fields: [
          "name", "quantity", {
            key: "id",
            label: "Link"
          }, {
            key: "list",
            label: "Shopping List"
          }
        ],
        items: [{
          id: 1,
          name: "kapusta",
          quantity: 1,
          bought: false,
          buyer: {
            id: 1,
            firstName: "Jano",
            lastName: "Mrkvicka",
            email: "janko.mrkvicka@email.com"
          },
          list: {
            id: 1,
            name: "Zakladne potreby",
          },
        }, {
          id: 2,
          name: "klobasa",
          quantity: 2,
          bought: false,
          buyer: {
            id: 1,
            firstName: "Jano",
            lastName: "Mrkvicka",
            email: "janko.mrkvicka@email.com"
          },
          list: {
            id: 1,
            name: "Zakladne potreby",
          },
        }],
        household_fields: ["street", "buildingNumber", "zipCode", "state", {
          key: "id",
          label: "Link",
        }],
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
        }],
      }
    }
  }
</script>

<style scoped>
</style>
