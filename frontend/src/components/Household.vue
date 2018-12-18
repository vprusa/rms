<template>
  <div class="mx-auto container">
    <b-card :title="household.street + ' ' + household.buildingNumber"
            :sub-title="household.zipCode + ' ' + household.state">
    </b-card>
    <div>
      <b-card-group deck>
        <b-card header="Shopping Lists"
                header-tag="header">
          <div v-if="lists && lists.length">
            <b-table responsive hover :items="lists" :fields="list_fields">
              <template slot="id" slot-scope="data">
                <router-link :to="{name: 'ShoppingList', param: {id: data.value}}">
                  Show
                </router-link>
              </template>
            </b-table>
            <div class="mx-auto container">
              <b-form inline>
                <b-form-input id="new_list" type="text" required placeholder="Food"></b-form-input>
                <b-button type="submit" variant="success">Create List</b-button>
              </b-form>
            </div>
          </div>
        </b-card>

        <b-card header="Users"
                header-tag="header">
          <b-table responsive hover :items="users" :fields="user_fields">
            <template slot="id" slot-scope="data">
              <router-link :to="{name: 'User', param: {id: data.value}}">Show</router-link>
            </template>
          </b-table>
          <div class="mx-auto container">
            <b-form inline>
              <b-form-input id="add_user" type="email" required placeholder="user@email.com"></b-form-input>
              <b-button type="submit" variant="success">Add User</b-button>
            </b-form>
          </div>
        </b-card>
      </b-card-group>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Household",
    data() {
      return {
        household: {
          id: 1,
          street: "Kratka",
          buildingNumber: "12a",
          zipCode: "44 212",
          state: "Czechia"
        },
        user_fields: [
          "firstName", "lastName", {
            key: "id",
            label: "Link"
          }
        ],
        users: [{
          id: 1,
          firstName: "Jano",
          lastName: "Mrkvicka",
          email: "janko.mrkvicka@email.com"
        }],
        list_fields: ["name", {
          key: "id",
          label: "Link"
        }],
        lists: [{
          id: 1,
          name: "Zakladne potreby",
        }]
      }
    }
  }
</script>

<style scoped>

</style>
