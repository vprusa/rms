<template>
  <div class="mx-auto container" v-if="household">
    <b-card :title="`${household.street} ${household.buildingNumber}`"
            :sub-title="`${household.zipCode}, ${household.state}`">
      <b-card-group>
        <b-card header="Tenants">
          <div v-if="tenants && tenants.length > 0">
            <b-table responsive :items="tenants" :fields="tenant_fields">
              <template slot="id" slot-scope="data">
                <router-link :to="{ name: 'User', params: { id: data.value }}">
                  <b-button variant="primary" v-if="data.value === $store.state.user.id">Check</b-button>
                  <b-button variant="danger" disabled v-else>Check</b-button>
                </router-link>
              </template>
            </b-table>
          </div>
          <div v-else>
            No tenants
          </div>
        </b-card>
        <b-card header="Shopping lists">
          <div v-if="lists && lists.length > 0">
            <b-table responsive :items="lists" :fields="list_fields">
              <template slot="shoppingItems" slot-scope="data">
                {{data.value.length}}
              </template>
              <template slot="household" slot-scope="data">
                <router-link :to="{name: 'Household', params: { id: data.value.id }}">
                  {{`${data.value.street} ${data.value.buildingNumber}`}}
                </router-link>
              </template>
              <template slot="id" slot-scope="data">
                <router-link :to="{name: 'ShoppingList', params: { id: data.value }}">
                  <b-button variant="primary">Check</b-button>
                </router-link>
              </template>
            </b-table>
          </div>
          <div v-else>
            No lists
          </div>
        </b-card>
      </b-card-group>
    </b-card>
  </div>
</template>

<script>
  import services from '@/rms-service'

  export default {
    name: "Household",
    data() {
      return {
        household_id: this.$route.params.id,
        household: null,
        tenant_fields: ['email', 'firstName', 'lastName', {
          key: 'id',
          label: 'Check',
        }],
        list_fields: ['name', {
          key: 'shoppingItems',
          label: 'To Buy'
        }, {
          key: 'household',
          label: 'Household'
        }, {
          key: 'id',
          label: 'Check',
        }],
      }
    },

    created() {
      this.getData()
    },

    computed: {
      tenants() {
        if (this.household != null && this.household.tenants != null) {
          return this.household.tenants;
        } else {
          return [];
        }
      },
      lists() {
        if (this.household != null && this.household.lists != null) {
          return this.household.lists;
        } else {
          return [];
        }
      }
    },

    methods: {
      async getData() {
        this.household = await services.getHousehold(this.household_id);
      }
    }
  }
</script>

<style scoped>

</style>
