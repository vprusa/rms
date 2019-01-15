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
        if (this.list != null) {
          return this.list.shoppingItems;
        }
      },
    },

    methods: {
      async getData() {
        this.list = services.getShoppingList(this.list_id)
      },
      async buyItem(id) {
        if (services.isDev()) {
          let item = this.items.find(i => i.id === id);
          item.bought = true;
        } else {
          services.buyShoppingItem(id);
          this.list = service.getShoppingList(this.list_id);
        }
      }
    }
  }
</script>

<style scoped>

</style>
