<template>
  <div class="mx-auto container" v-if="list">
    <b-card :title="list.name">
      <b-card header="Items">
        <b-table :items="items" :fields="item_fields" :sort-by.sync="sortBy" :sort-desc.sync="sortDesc">
          <template slot="buyer" slot-scope="data">
            <div v-if="data.value">{{`${data.value.firstName} ${data.value.lastName}`}}</div>
            <div v-else>Unassigned</div>
          </template>
          <template slot="id" slot-scope="cell">
            <b-button variant="primary" @click="buyItem(cell.value)" v-if="!cell.item.bought">Buy</b-button>
            <b-badge variant="success" disabled v-else>Bought</b-badge>
          </template>
        </b-table>
      </b-card>
    </b-card>
  </div>
</template>

<script>
  import services from '@/rms-service'

  export default {
    name: "ShoppingList",
    data() {
      return {
        sortBy: 'bought',
        sortDesc: false,
        list_id: this.$route.params.id,
        list: null,
        item_fields: [
          {
            key: 'name',
            sortable: true
          }, 'quantity',
          {
            key: 'buyer',
            sortable: true,
          },
          {
            key: 'id',
            label: 'Buy',
            sortable: true,
          }
        ],
      }
    },

    created() {
      this.getData();
    },

    computed: {
      items() {
        console.log("computed");
        if (this.list != null) {
          console.log("computedNotNull");
          return this.list.shoppingItems;
        }
      },
    },

    methods: {
      async getData() {
        console.log("getData");
        this.list = await services.getCurrentHousheoldShoppingList(this.list_id)
      },
      async buyItem(id) {
        console.log("buyItem");
        if (services.isDev()) {
          let item = this.items.find(i => i.id === id);
          item.bought = true;
        } else {
          await services.buyShoppingItem(id);
          this.list = await services.getCurrentHousheoldShoppingList(this.list_id);
        }
      }
    }
  }
</script>

<style scoped>

</style>
