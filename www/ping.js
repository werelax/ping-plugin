/* global cordova */

"use strict";

module.exports = {
  send: function(cb, cberr) {
    cordova.exec(cb, cberr, "PingPlugin", "ping");
  }
};
