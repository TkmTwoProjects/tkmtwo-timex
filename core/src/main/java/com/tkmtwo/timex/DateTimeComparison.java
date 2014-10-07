/*
 *
 * Copyright 2014 Tom Mahaffey
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
 *
 */

package com.tkmtwo.timex;



import org.joda.time.DateTime;




/**
 * Enumeration for comparing <code>DateTime</code> values.
 *
 *
 * @author Tom Mahaffey
 *
 */
public enum DateTimeComparison {

  /** Less than. */
  LT {
    public  boolean compare(DateTime leftDt, DateTime rightDt) {
      if (leftDt == null || rightDt == null) { return false; }
      return leftDt.isBefore(rightDt);
    }
  },

  /** Less than or equal to. */
  LTEQ {
    public  boolean compare(DateTime leftDt, DateTime rightDt) {
      if (leftDt == null || rightDt == null) { return false; }
      return leftDt.isBefore(rightDt) || leftDt.isEqual(rightDt);
    }
  },

  /** Equal to. */
  EQ {
    public  boolean compare(DateTime leftDt, DateTime rightDt) {
      if (leftDt == null || rightDt == null) { return false; }
      return leftDt.isEqual(rightDt);
    }
  },

  /** Not equal to. */
  NEQ {
    public  boolean compare(DateTime leftDt, DateTime rightDt) {
      if (leftDt == null || rightDt == null) { return false; }
      return !leftDt.isEqual(rightDt);
    }
  },

  /** Greater than or equal to. */
  GTEQ {
    public  boolean compare(DateTime leftDt, DateTime rightDt) {
      if (leftDt == null || rightDt == null) { return false; }
      return leftDt.isAfter(rightDt) || leftDt.isEqual(rightDt);
    }
  },

  /** Greater than. */
  GT {
    public  boolean compare(DateTime leftDt, DateTime rightDt) {
      if (leftDt == null || rightDt == null) { return false; }
      return leftDt.isAfter(rightDt);
    }
  };


  public abstract boolean compare(DateTime leftDt, DateTime rightDt);
}



