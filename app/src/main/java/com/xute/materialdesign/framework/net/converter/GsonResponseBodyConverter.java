/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xute.materialdesign.framework.net.converter;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.TypeAdapter;

import java.net.URLEncoder;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) {
        if (value == null) {
//            throw new AppException("", "ResponseBody = null");
        }
        try {
            if (value.contentType() != null) {
//                Timber.v("Response contentType " + value.contentType().type());
                if ("image".equals(value.contentType().type())) {
                    return (T) value.bytes();
                }
            }
            String jsonStr = value.string();

//            Timber.v("Response body " + jsonStr);
            Log.i("LOG", "Response body>>>>>>>" + jsonStr);
            //容错处理
            if (TextUtils.isEmpty(jsonStr) || jsonStr.equals("\n")) {
                jsonStr = "{}";
//                Timber.v("容错处理后：" + jsonStr);
            } else {
                if (!jsonStr.startsWith("{") && !jsonStr.startsWith("[")) {
//                    Timber.e("Response body " + URLEncoder.encode(jsonStr, "utf-8"));
                    if (jsonStr.contains("\n")) {
                        jsonStr = jsonStr.replace("\n", "");
                    }
                    jsonStr = String.format("{\"result\":%s}", jsonStr);
//                    Timber.v("容错处理后：" + jsonStr);
                }
            }

            return adapter.fromJson(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
//            throw new AppException("", "Json format convert exception " + e.getMessage());
            return null;
        } finally {
            value.close();
        }
    }
}