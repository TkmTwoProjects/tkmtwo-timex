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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;



/**
 * Interval using WallClocks.
 *
 * Similar in spirit to JodaTime's Interval, a WallClockInterval is inclusive
 * of the start and exlusive of the end.
 *
 */
public final class WallClockInterval
  implements Comparable<WallClockInterval> {
  private final WallClock start;
  private final WallClock end;



  /**
   * Creates a new zero-duration <code>WallClockInterval</code> instance.
   *
   * <p>
   * Uses <code>wc</code> for both start and end.
   *
   * @param wc a <code>WallClock</code> value
   */
  public WallClockInterval(WallClock wc) {
    this(wc, wc);
  }


  /**
   * Creates a new <code>WallClockInterval</code> instance.
   *
   * @param wcStart a <code>WallClock</code> value
   * @param wcEnd a <code>WallClock</code> value
   */
  public WallClockInterval(WallClock wcStart,
                           WallClock wcEnd) {
    checkNotNull(wcStart, "Need a start WallClock.");
    checkNotNull(wcEnd, "Need an end WallClock.");
    checkArgument(wcEnd.isAfterOrEquals(wcStart),
                  "End must be greater than or equal to start.");

    start = wcStart;
    end = wcEnd;
  }


  /**
   * Gets the start <code>WallClock</code> value.
   *
   * @return a <code>WallClock</code> value
   */
  public WallClock getStart() { return start; }

  /**
   * Gets the seconds value of the start <code>WallClock</code>.
   *
   * @return an <code>int</code> value
   */
  public int getStartSeconds() { return getStart().getSeconds(); }




  /**
   * Gets the end <code>WallClock</code> value.
   *
   * @return a <code>WallClock</code> value
   */
  public WallClock getEnd() { return end; }



  /**
   * Gets the seconds value of the end <code>WallClock</code>.
   *
   * @return an <code>int</code> value
   */
  public int getEndSeconds() { return getEnd().getSeconds(); }




  /**
   * Tells whether this <code>WallClockInterval</code> overlaps with another.
   *
   * <p>
   * This method uses the same rationale as JodaTime's <code>Interval.overlaps()</code>.
   *
   * @param wci a <code>WallClockInterval</code> value
   * @return a <code>boolean</code> value
   * @see org.joda.time.base.AbstractInterval#overlaps(org.joda.time.ReadableInterval)
   */
  public boolean overlaps(WallClockInterval wci) {
    checkNotNull(wci, "Need a WallClockInterval.");

    int thisStart = getStartSeconds();
    int thisEnd = getEndSeconds();
    int otherStart = wci.getStartSeconds();
    int otherEnd = wci.getEndSeconds();

    return (thisStart < otherEnd
            && otherStart < thisEnd);
  }


  /**
   * Calculates the overlap interval between this <code>WallClock</code> and another.
   *
   * <p>
   * This method uses the same rationale as JodaTime's <code>Interval.overlaps()</code>
   *
   * @param wci a <code>WallClockInterval</code> value
   * @return a <code>WallClockInterval</code> value
   */
  public WallClockInterval overlap(WallClockInterval wci) {
    if (overlaps(wci) == false) { return null; }

    int startInt = Math.max(getStartSeconds(), wci.getStartSeconds());
    int endInt   = Math.min(getEndSeconds(), wci.getEndSeconds());

    return new WallClockInterval(WallClock.valueOf(startInt),
                                 WallClock.valueOf(endInt));

  }




  /**
   * Tells whether this <code>WallClockInterval</code> abuts with another.
   *
   * <p>
   * This method uses the same rationale as JodaTime's <code>Interval.abuts()</code>
   *
   *
   * @param wci a <code>WallClockInterval</code> value
   * @return a <code>boolean</code> value
   */
  public boolean abuts(WallClockInterval wci) {
    checkNotNull(wci, "Need a WallClockInterval.");

    int thisStart = getStartSeconds();
    int thisEnd = getEndSeconds();
    int otherStart = wci.getStartSeconds();
    int otherEnd = wci.getEndSeconds();

    return (thisStart == otherEnd
            || thisEnd == otherStart);
  }



  /**
   * Calculates the gap interval between this <code>WallClock</code> and another.
   *
   * <p>
   * This method uses the same rationale as JodaTime's <code>Interval.gap()</code>.
   *
   * @param wci a <code>WallClockInterval</code> value
   * @return a <code>WallClockInterval</code> value
   */
  public WallClockInterval gap(WallClockInterval wci) {
    checkNotNull(wci, "Need a WallClockInterval.");

    int thisStart = getStartSeconds();
    int thisEnd = getEndSeconds();
    int otherStart = wci.getStartSeconds();
    int otherEnd = wci.getEndSeconds();

    if  (thisStart > otherEnd) {
      return new WallClockInterval(wci.getEnd(), getStart());
    }
    if (otherStart > thisEnd) {
      return new WallClockInterval(getEnd(), wci.getStart());
    }
    return null;

  }





  /**
   * Tells whether a specific seconds of day value falls in this interval.
   *
   * @param secsOfDay an <code>int</code> value
   * @return a <code>boolean</code> value
   */
  public boolean contains(int secsOfDay) {
    return (secsOfDay >= getStartSeconds()
            && secsOfDay < getEndSeconds());
  }

  /**
   * Tells whether a <code>WallClock</code> falls in this interval.
   *
   * @param wc a <code>WallClock</code> value
   * @return a <code>boolean</code> value
   */
  public boolean contains(WallClock wc) {
    checkNotNull(wc, "Need a WallClock.");
    return contains(wc.getSeconds());
  }

  /**
   * Tells whether another <code>WallClockInterval</code> is contained in this one.
   *
   * <p>
   * This method uses the same rationale as JodaTime's <code>Interval.contains()</code>.
   *
   * @param wci a <code>WallClockInterval</code> value
   * @return a <code>boolean</code> value
   */
  public boolean contains(WallClockInterval wci) {
    checkNotNull(wci, "Need a WallClockInterval.");

    int thisStart = getStartSeconds();
    int thisEnd = getEndSeconds();
    int otherStart = wci.getStartSeconds();
    int otherEnd = wci.getEndSeconds();

    return (thisStart <= otherStart
            && otherStart < thisEnd
            && otherEnd <= thisEnd);
  }





  /**
   * Formats the interval using 'HHmmss/HHmmss'.
   *
   * @return a <code>String</code> value
   */
  public String print() { return printBasic(); }

  /**
   * Formats the interval using 'HHmmss/HHmmss'.
   *
   * @return a <code>String</code> value
   */
  public String printBasic() {
    return String.format("%02d%02d%02d/%02d%02d%02d",
                         getStart().getHour(),
                         getStart().getMinute(),
                         getStart().getSecond(),
                         getEnd().getHour(),
                         getEnd().getMinute(),
                         getEnd().getSecond());
  }

  /**
   * Formats the interval using 'HH:mm:ss/HH:mm:ss'.
   *
   * @return a <code>String</code> value
   */
  public String printExtended() {
    return String.format("%02d:%02d:%02d/%02d:%02d:%02d",
                         getStart().getHour(),
                         getStart().getMinute(),
                         getStart().getSecond(),
                         getEnd().getHour(),
                         getEnd().getMinute(),
                         getEnd().getSecond());
  }






  @Override
  public boolean equals(Object obj) {
    if (obj == null) { return false; }
    if (getClass() != obj.getClass()) { return false; }

    final WallClockInterval impl = (WallClockInterval) obj;
    return
      Objects.equal(getStart(), impl.getStart())
      && Objects.equal(getEnd(), impl.getEnd());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getStart(), getEnd());
  }

  @Override
  public int compareTo(WallClockInterval wci) {
    return ComparisonChain.start()
      .compare(getStart(), wci.getStart())
      .compare(getEnd(), wci.getEnd())
      .result();
  }




}

