<script setup>
import ipa from './IpaSymbol.vue'

</script>
<template>
  <main id="ipa-chart">
    <el-card>
      <template #header>
        <el-text type="primary" size="large" style="font-size: xxx-large;">元音 (20)</el-text>
      </template>
      <el-row :gutter="20">
        <el-col :span="4" :lg="6">
          <el-text type="primary" size="large" style="font-size: x-large;">长元音</el-text>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>iː</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>uː</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ɜː</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ɔː</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ɑː</ipa>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-text type="primary" size="large" style="font-size: x-large;">短元音</el-text>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ɪ</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ʊ</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ə</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ɔ</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ʌ</ipa>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="4" :lg="6"></el-col>
        <el-col :span="2" :lg="3">
          <ipa>e</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>æ</ipa>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="4" :lg="6">
          <el-text type="primary" size="large" style="font-size: x-large;">双元音</el-text>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>eɪ</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>aɪ</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ɔɪ</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ɪə</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>eə</ipa>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="4" :lg="6"></el-col>
        <el-col :span="2" :lg="3">
          <ipa>ʊə</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>əʊ</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>aʊ</ipa>
        </el-col>
      </el-row>
    </el-card>

    <el-card style="margin-top: 1rem;">
      <template #header>
        <el-text type="primary" size="large" style="font-size: xxx-large;">辅音 (28)</el-text>
      </template>
      <el-row>
        <el-col :span="3" :lg="6">
          <el-text type="primary" size="large" style="font-size: x-large;">清辅音</el-text>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>p</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>t</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>k</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>f</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>θ</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>s</ipa>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="3" :lg="6">
          <el-text type="primary" size="large" style="font-size: x-large;">浊辅音</el-text>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>b</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>d</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>g</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>v</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ð</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>z</ipa>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="3" :lg="6">
          <el-text type="primary" size="large" style="font-size: x-large;">清辅音</el-text>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ʃ</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>h</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ts</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>tʃ</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>tr</ipa>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="3" :lg="6">
          <el-text type="primary" size="large" style="font-size: x-large;">清辅音</el-text>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ʒ</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>r</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>dz</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>dʒ</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>dr</ipa>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="3" :lg="6">
          <el-text type="primary" size="large" style="font-size: x-large;">鼻音</el-text>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>m</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>n</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>ŋ</ipa>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="3" :lg="6">
          <el-text type="primary" size="large" style="font-size: x-large;">舌侧音</el-text>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>l</ipa>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="3" :lg="6">
          <el-text type="primary" size="large" style="align-self: center; font-size: x-large;">半元音</el-text>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>j</ipa>
        </el-col>
        <el-col :span="2" :lg="3">
          <ipa>w</ipa>
        </el-col>
      </el-row>
    </el-card>
  </main>
</template>
<style scoped>
#ipa-chart el-row {
  margin-top: 0.5rem !important;
}
</style>