<template>
  <div class="max-w-xl lg:max-w-5xl mx-auto px-4 py-[5vh]">
    <h1 class="text-5xl text-indigo-100 text-center mb-6">Search</h1>
    <Help></Help>
    <h2
      class="
        text-white
        font-semibold
        text-center
        my-5
        underline underline-offset-2
      "
    >
      <a
        class="flex space-x-1 items-center justify-center"
        href="https://www.seqwa.com"
        rel="noreferrer"
        target="_blank"
      >
        <span>Seqwa | AI-Powered Semantic Search</span>
        <svg
          viewBox="0 0 24 24"
          fill="none"
          class="h-4 w-4 lg:h-6 lg:w-6 fill-current"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            d="M11.2716 7.25C11.2716 6.83579 11.6073 6.5 12.0216 6.5H16.75C17.1642 6.5 17.5 6.83579 17.5 7.25V11.9785C17.5 12.3927 17.1642 12.7285 16.75 12.7285C16.3358 12.7285 16 12.3927 16 11.9785V9.06066L10.7803 14.2803C10.4874 14.5732 10.0126 14.5732 9.71967 14.2803C9.42678 13.9874 9.42678 13.5126 9.71967 13.2197L14.9393 8H12.0216C11.6073 8 11.2716 7.66421 11.2716 7.25Z"
          ></path>
          <path
            d="M6.15675 5.25C6.57853 3.94437 7.80398 3 9.25 3H17.75C19.5449 3 21 4.45507 21 6.25V14.8382C21 16.1902 20.1745 17.3494 19 17.8392V17.9751C19 19.0247 18.4705 19.82 17.6913 20.3191C16.9414 20.7995 15.9737 21.0045 14.9977 20.9999L14.9963 20.9999L10.9191 20.9952L7 20.9951C5.84332 20.9951 4.83555 20.6327 4.11066 19.9496C3.38287 19.2639 3 18.3103 3 17.25V8.5C3 7.63484 3.21573 6.81725 3.73399 6.20358C4.26793 5.57135 5.04354 5.25 5.95588 5.25H6.15675ZM6 6.75H5.95588C5.41235 6.75 5.08501 6.92865 4.87998 7.17142C4.65927 7.43275 4.5 7.86516 4.5 8.5V17.25C4.5 17.9397 4.74213 18.4837 5.13934 18.8579C5.53944 19.2349 6.15669 19.4951 7 19.4951L10.9191 19.4952L15.0009 19.4999L15.0037 19.4999C15.7919 19.5038 16.4486 19.3338 16.8822 19.0561C17.254 18.8179 17.4641 18.5061 17.4958 18.0882H9.25C7.45508 18.0882 6 16.6332 6 14.8382V6.75ZM9.25 4.5C8.2835 4.5 7.5 5.2835 7.5 6.25V14.8382C7.5 15.8047 8.2835 16.5882 9.25 16.5882H17.75C18.7165 16.5882 19.5 15.8047 19.5 14.8382V6.25C19.5 5.2835 18.7165 4.5 17.75 4.5H9.25Z"
          ></path>
        </svg>
      </a>
    </h2>

    <TabGroup>
      <TabList class="flex p-1 space-x-1 bg-blue-900/20 rounded-xl">
        <Tab
          v-for="category in tabs"
          as="template"
          :key="category"
          v-slot="{ selected }"
        >
          <button
            :class="[
              'w-full py-2.5 text-sm leading-5 font-medium text-blue-700 rounded-lg',
              'focus:outline-none focus:ring-2 ring-offset-2 ring-offset-blue-400 ring-white ring-opacity-60',
              selected
                ? 'bg-white shadow'
                : 'text-blue-100 hover:bg-white/[0.12] hover:text-white',
            ]"
          >
            {{ category }}
          </button>
        </Tab>
      </TabList>

      <TabPanels class="mt-2">
        <TabPanel>
          <div class="relative mt-1">
            <div class="flex space-x-3">
              <input
                class="
                  w-full
                  border-none
                  rounded-lg
                  focus:outline-none focus:ring-2 focus:ring-indigo-100
                  py-2
                  pl-3
                  pr-10
                  text-sm
                  leading-5
                  text-gray-900
                "
                @input="query = $event.target.value"
                :defaultValue="query"
              />
              <button
                type="button"
                @click="unrankedSearch"
                class="
                  px-4
                  py-2
                  text-sm
                  font-medium
                  bg-white
                  text-indigo-700
                  rounded-md
                  focus:outline-none
                  focus-visible:ring-2
                  focus-visible:ring-white
                  focus-visible:ring-opacity-75
                "
              >
                Search
              </button>
            </div>
            <div v-if="searching"><Searching /></div>
            <div
              v-if="
                searchResults &&
                searchResults.searchResults &&
                searchResults.searchResults.length === 0 &&
                query !== ''
              "
              class="rounded-lg shadow-lg ring-1 ring-black ring-opacity-5 mt-2"
            >
              <div
                class="
                  relative
                  rounded-lg
                  grid
                  gap-8
                  bg-white
                  p-7
                  lg:grid-cols-2
                "
              >
                <div
                  class="
                    cursor-default
                    select-none
                    relative
                    py-2
                    px-4
                    text-gray-700
                  "
                >
                  Nothing found.
                </div>
              </div>
            </div>
            <div
              v-if="searchResults && searchResults.searchResults"
              class="rounded-lg shadow-lg ring-1 ring-black ring-opacity-5 mt-2"
            >
              <div
                class="
                  relative
                  rounded-lg
                  grid
                  gap-8
                  bg-white
                  p-7
                  lg:grid-cols-2
                "
              >
                <a
                  v-for="(item, index) in searchResults.searchResults"
                  as="template"
                  :key="index"
                  :value="item"
                  :href="item.link"
                  rel="noreferrer"
                  target="_blank"
                  class="
                    flex
                    items-center
                    p-2
                    -m-3
                    transition
                    duration-150
                    ease-in-out
                    rounded-lg
                    hover:bg-slate-100
                    focus:outline-none
                    focus-visible:ring
                    focus-visible:ring-orange-500
                    focus-visible:ring-opacity-50
                    cursor-pointer
                  "
                >
                  <div
                    class="
                      flex
                      items-center
                      justify-center
                      flex-shrink-0
                      w-10
                      h-10
                      text-slate-600 text-xs
                      sm:h-12 sm:w-12
                    "
                  >
                    <img :src="item.image" alt="Not Found" />
                  </div>
                  <div class="ml-4">
                    <p class="text-sm font-medium text-gray-900">
                      {{ item.title }}
                    </p>
                    <p class="text-sm text-gray-500">{{ item.price }}</p>
                  </div>
                </a>
              </div>
            </div>
          </div>
        </TabPanel>
        <TabPanel>
          <div class="relative mt-1">
            <input
              class="
                w-full
                border-none
                rounded-lg
                focus:outline-none focus:ring-2 focus:ring-indigo-100
                py-2
                pl-3
                pr-10
                text-sm
                leading-5
                text-gray-900
              "
              @input="query = $event.target.value"
              :defaultValue="query"
            />
            <div class="flex space-x-3 mt-3">
              <input
                class="
                  w-full
                  border-none
                  rounded-lg
                  focus:outline-none focus:ring-2 focus:ring-indigo-100
                  py-2
                  pl-3
                  pr-10
                  text-sm
                  leading-5
                  text-gray-900
                "
                @input="context = $event.target.value"
                :defaultValue="context"
              />
              <button
                type="button"
                @click="rankedSearch"
                class="
                  px-4
                  py-2
                  text-sm
                  font-medium
                  bg-white
                  text-indigo-700
                  rounded-md
                  focus:outline-none
                  focus-visible:ring-2
                  focus-visible:ring-white
                  focus-visible:ring-opacity-75
                "
              >
                Search
              </button>
            </div>
            <div v-if="searching"><Searching /></div>
            <div
              v-if="
                rankedSearchResults &&
                rankedSearchResults.searchResults &&
                rankedSearchResults.searchResults.length === 0 &&
                query !== ''
              "
              class="rounded-lg shadow-lg ring-1 ring-black ring-opacity-5 mt-2"
            >
              <div
                class="
                  relative
                  rounded-lg
                  grid
                  gap-8
                  bg-white
                  p-7
                  lg:grid-cols-2
                "
              >
                <div
                  class="
                    cursor-default
                    select-none
                    relative
                    py-2
                    px-4
                    text-gray-700
                  "
                >
                  Nothing found.
                </div>
              </div>
            </div>
            <div
              v-if="rankedSearchResults && rankedSearchResults.searchResults"
              class="rounded-lg shadow-lg ring-1 ring-black ring-opacity-5 mt-2"
            >
              <div
                class="
                  relative
                  rounded-lg
                  grid
                  gap-8
                  bg-white
                  p-7
                  lg:grid-cols-2
                "
              >
                <a
                  v-for="(item, index) in rankedSearchResults.searchResults"
                  as="template"
                  :key="index"
                  :value="item"
                  :href="item.link"
                  rel="noreferrer"
                  target="_blank"
                  class="
                    flex
                    items-center
                    p-2
                    -m-3
                    transition
                    duration-150
                    ease-in-out
                    rounded-lg
                    hover:bg-slate-100
                    focus:outline-none
                    focus-visible:ring
                    focus-visible:ring-orange-500
                    focus-visible:ring-opacity-50
                    cursor-pointer
                  "
                >
                  <div
                    class="
                      flex
                      items-center
                      justify-center
                      flex-shrink-0
                      w-10
                      h-10
                      text-slate-600 text-xs
                      sm:h-12 sm:w-12
                    "
                  >
                    <img :src="item.image" alt="Not Found" />
                  </div>
                  <div class="ml-4">
                    <p class="text-sm font-medium text-gray-900">
                      {{ item.title }}
                    </p>
                    <p class="text-sm text-gray-500">{{ item.price }}</p>
                  </div>
                </a>
              </div>
            </div>
          </div>
        </TabPanel>
      </TabPanels>
    </TabGroup>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import {
  TransitionRoot,
  TabGroup,
  TabList,
  Tab,
  TabPanels,
  TabPanel,
} from "@headlessui/vue";
import { CheckIcon, SelectorIcon } from "@heroicons/vue/solid";
import Help from "./Help.vue";
import Searching from "./Searching.vue";

const tabs = ["Full-text Search", "Semantically Re-ranked"];
let selected = ref();
</script>

<script>
let query = ref("");
let context = ref("");
let searchResults = ref([]);
let rankedSearchResults = ref([]);
let searching = ref(false);
export default {
  methods: {
    unrankedSearch: async () => {
      searching.value = true;
      if (query.value) {
        searchResults.value = await (
          await fetch("/api/search?query=" + query.value)
        ).json();
      }
      searching.value = false;
    },
    rankedSearch: async () => {
      searching.value = true;
      if (query.value) {
        rankedSearchResults.value = await (
          await fetch(
            "/api/search?query=" +
              query.value +
              "&context=" +
              context.value +
              "&type=ranked"
          )
        ).json();
      }
      searching.value = false;
    },
  },
};
</script>
