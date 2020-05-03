package edu.ucmo.nacha.record.intermediate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link DefaultIndexTracker} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultIndexTrackerTest {

  // Fixtures
  private DefaultIndexTracker indexTracker;

  @Test
  public void getNext__StartsSequenceAtZero__Test() {
    assertThat(indexTracker.getNext()).isEqualTo(0);
    assertThat(indexTracker.getNext()).isEqualTo(1);
    assertThat(indexTracker.getNext()).isEqualTo(2);
  }

  @Before
  public void setUp() {
    indexTracker = new DefaultIndexTracker();
  }
}
