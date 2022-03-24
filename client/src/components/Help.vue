<template>
  <div>
    <div class="flex items-center justify-center">
      <button
        type="button"
        @click="openModal"
        class="
          px-4
          py-2
          text-sm
          font-medium
          text-white
          bg-black
          rounded-md
          bg-opacity-20
          hover:bg-opacity-30
          focus:outline-none
          focus-visible:ring-2
          focus-visible:ring-white
          focus-visible:ring-opacity-75
        "
      >
        Help
      </button>
    </div>
    <TransitionRoot appear :show="isOpen" as="template">
      <Dialog as="div" @close="closeModal">
        <div class="fixed inset-0 z-10 overflow-y-auto">
          <div class="min-h-screen px-4 text-center">
            <TransitionChild
              as="template"
              enter="duration-300 ease-out"
              enter-from="opacity-0"
              enter-to="opacity-100"
              leave="duration-200 ease-in"
              leave-from="opacity-100"
              leave-to="opacity-0"
            >
              <DialogOverlay class="fixed inset-0 bg-slate-900/70" />
            </TransitionChild>

            <span class="inline-block h-screen align-middle" aria-hidden="true">
              &#8203;
            </span>

            <TransitionChild
              as="template"
              enter="duration-300 ease-out"
              enter-from="opacity-0 scale-95"
              enter-to="opacity-100 scale-100"
              leave="duration-200 ease-in"
              leave-from="opacity-100 scale-100"
              leave-to="opacity-0 scale-95"
            >
              <div
                class="
                  inline-block
                  w-full
                  max-w-xl
                  p-6
                  my-8
                  overflow-hidden
                  text-left
                  align-middle
                  transition-all
                  transform
                  bg-white
                  shadow-xl
                  rounded-2xl
                "
              >
                <DialogTitle
                  as="h3"
                  class="text-lg font-medium leading-6 text-gray-900"
                >
                  Help
                </DialogTitle>
                <div class="mt-4">
                  <p class="text-indigo-500 font-medium">
                    Search function lets you do full-text search and semantic
                    re-ranking with search query and context.
                  </p>
                  <p class="text-sm text-gray-900 font-medium mt-4">
                    Full-text Search
                  </p>
                  <ul class="list-inside list-disc text-sm text-gray-700 my-2">
                    <li>Enter keywords and click the Search button.</li>
                    <li>You can add filters to provide faceted search.</li>
                    <li>This example only covers simple text search</li>
                  </ul>
                  <p class="text-sm text-gray-900 font-medium mt-4">
                    Semantic Re-ranking
                  </p>
                  <ul class="list-inside list-disc text-sm text-gray-700 my-2">
                    <li>Same as full-text search enter keywords</li>
                    <li>
                      The Context can be assumed as a title or description of
                      one of more items previously searched by a user
                    </li>
                    <li>Click the Search button</li>
                    <li>
                      If you no context is provided the search results will be
                      ranked based on the Search query
                    </li>
                  </ul>
                </div>

                <div class="mt-6">
                  <button
                    type="button"
                    class="
                      inline-flex
                      justify-center
                      px-4
                      py-2
                      text-sm
                      font-medium
                      text-blue-900
                      bg-blue-100
                      border border-transparent
                      rounded-md
                      hover:bg-blue-200
                      focus:outline-none
                      focus-visible:ring-2
                      focus-visible:ring-offset-2
                      focus-visible:ring-blue-500
                    "
                    @click="closeModal"
                  >
                    Got it, thanks!
                  </button>
                </div>
              </div>
            </TransitionChild>
          </div>
        </div>
      </Dialog>
    </TransitionRoot>
  </div>
</template>

<script setup>
import { ref } from "vue";
import {
  TransitionRoot,
  TransitionChild,
  Dialog,
  DialogOverlay,
  DialogTitle,
} from "@headlessui/vue";

const isOpen = ref(true);

function closeModal() {
  isOpen.value = false;
}
function openModal() {
  isOpen.value = true;
}
</script>
