module.exports = {
  devServer: {
    proxy: {
      "/": {
        changeOrigin: true,
        target: 'http://localhost:8080/'
      }
    }
  }
}
