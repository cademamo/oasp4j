package io.oasp.gastronomy.restaurant.offermanagement.batch.impl.reader;

import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.DrinkEto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

/**
 * Implementation of ItemReader. It reads data from csv file and creates DrinkEto.
 *
 * @author jczas
 */
public class DrinkFromCsvReader extends FlatFileItemReader<DrinkEto> {

  private static final Logger LOG = LoggerFactory.getLogger(DrinkFromCsvReader.class);

  /**
   * The constructor.
   */
  public DrinkFromCsvReader() {

    super();

    // setting resource
    // TODO set resource from parameters (???)
    setResource(new ClassPathResource("drinks.csv"));

    // setting line tokenizer and mapper
    DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
    delimitedLineTokenizer.setNames(new String[] { "name", "description" });

    BeanWrapperFieldSetMapper beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<DrinkEto>();
    beanWrapperFieldSetMapper.setTargetType(DrinkEto.class);

    DefaultLineMapper defaultLineMapper = new DefaultLineMapper<DrinkEto>();
    defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
    defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

    setLineMapper(defaultLineMapper);
  }

}
