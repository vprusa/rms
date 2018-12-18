<template>
  <div class="mx-auto container">
    <b-card :title="list.name">
      <b-table responsive hover :items="toBuy()" :fields="item_fields">
        <template slot="buyer" slot-scope="data">
          <router-link :to="{name: 'User', param: {id: data.value.id}}" tag="b-button" v-if="data.value">Show
          </router-link>
          <b-form inline v-else>
            <b-form-input type="email" required placeholder="user@email.com"></b-form-input>
            <b-button type="submit" variant="primary">Set Buyer</b-button>
          </b-form>
        </template>
        <template slot="id" slot-scope="data">
          <b-button variant="success">Buy</b-button>
        </template>
      </b-table>
      <form></form>
    </b-card>
  </div>
</template>

<script>
  // private Long id;
  // private String name;
  // private List<ShoppingItemDTO> shoppingItems;
  // private HouseholdDTO household;

  export default {
    name: "ShoppingList",
    data() {
      return {
        toBuy() {
          return items.filter(i => i.bought);
        },
        list: {
          id: 1,
          name: "Zakladne potreby",
        },
        item_fields: ["name", "quantity", "buyer", {
          key: "id",
          label: "Buy",
        }],
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
        }, {
          id: 3,
          name: "horcica",
          quantity: 1,
          bought: false,
          buyer: null,
          list: {
            id: 1,
            name: "Zakladne potreby",
          },
        }],
      };
    },
  }
</script>

<style scoped>

</style>
