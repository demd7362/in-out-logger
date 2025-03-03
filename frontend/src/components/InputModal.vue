<script setup lang="ts">
import { useStore } from 'vuex'
import { computed, nextTick, ref, watch } from 'vue'

const store = useStore()
const isModalOpen = computed(() => store.getters.isInputModalOpen)
const handleClose = computed(() => store.getters.handleModalClose)
const handleConfirm = computed(() => store.getters.handleModalConfirm)
const inputValue = computed({
  get: () => store.getters.getInputValue,
  set: (value) => store.commit('setInputValue', value)
})
const closeText = computed(() => store.getters.getCloseText)
const confirmText = computed(() => store.getters.getConfirmText)
const disableConfirm = computed(() => store.getters.getDisableConfirm)
const closeModalWithoutSideEffect = () => {
  store.commit('setModal', {
    isOpen: false
  })
}

const inputRef = ref()

watch([isModalOpen],async () => {
  if(isModalOpen.value && inputRef.value) {
    await nextTick()
    inputRef.value.focus()
  }
})

</script>
<template>
  <div class="fixed inset-0 z-50 flex items-center justify-center overflow-auto bg-black bg-opacity-50" v-show="isModalOpen" @click="closeModalWithoutSideEffect">
    <div class="relative bg-white rounded-lg shadow-xl p-6 m-4 max-w-xl w-full" @click.stop="">
      <div v-show="isModalOpen" class="mt-4">
        <component :is="store.getters.getContent" class="text-lg mb-4"></component>
        <input
          ref="inputRef"
          v-model="inputValue"
          @keyup.enter="handleConfirm"
          type="text"
          id="modalInput"
          class="w-full px-3 py-2 text-gray-700 border rounded-lg focus:outline-none focus:border-blue-500 mb-4"
          :placeholder="store.getters.getPlaceholder"
        />
        <div class="flex justify-end space-x-4">
          <button class="px-4 py-2 bg-gray-300 text-gray-800 rounded hover:bg-gray-400 transition duration-300" @click="handleClose">{{ closeText }}</button>
          <button :disabled="disableConfirm" class="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600 transition duration-300" @click="handleConfirm">{{ confirmText }}</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
