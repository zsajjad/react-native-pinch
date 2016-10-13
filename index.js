'use strict';

import { NativeModules } from 'react-native';

var RNPinch = {
    fetch: function (url, obj, callback){
        NativeModules.RNPinch.fetch(url, obj, (err, res) => {
            callback(err, res);
        });
    }
};


module.exports =  RNPinch;