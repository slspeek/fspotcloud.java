/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fspotcloud.server.mapreduce;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.tools.mapreduce.AppEngineMapper;

import org.apache.hadoop.io.NullWritable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * My first mapper: The Photo counter
 */
public class PhotoCountMapper extends AppEngineMapper<Key, Entity, NullWritable, NullWritable> {
  private static final Logger log = Logger.getLogger(PhotoCountMapper.class.getName());

  public PhotoCountMapper() {
  }

  @Override
  public void taskSetup(Context context) {
    log.warning("Doing per-task setup");
  }

  @Override
  public void taskCleanup(Context context) {
    log.warning("Doing per-task cleanup");
  }

  @Override
  public void setup(Context context) {
    log.warning("Doing per-worker setup");
  }

  @Override
  public void cleanup(Context context) {
    log.warning("Doing per-worker cleanup");
  }

  // This is a silly mapper that's intended to show some of the capabilities of the API.
  @Override
  public void map(Key key, Entity value, Context context) {
    log.warning("Mapping key: " + key);
    context.getCounter("Photos", "Total").increment(1);
    if (value.hasProperty("imageLoaded")) {
      // These counts are aggregated and can be seen on the status page.
      Boolean loaded = (Boolean) value.getProperty("imageLoaded"); 
      if (loaded != null && loaded == true ) {
        context.getCounter("Photo", "Images").increment(1);
      } 
    }
  }
}