package com.peaceful.demo.es.sqlconvert;

import com.peaceful.common.util.chain.Command;
import com.peaceful.common.util.chain.Context;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;

/**
 * sql > 、>=
 * <p/>
 * Created by wangjun on 16/2/17.
 */
public class Ge implements Command {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean execute(Context context) throws Exception {
        String word = (String) context.get(SqlContextConst.CURRENT_WOLD);
        String[] words = (String[]) context.get(SqlContextConst.WORLDS);
        int pointer = (Integer) context.get(SqlContextConst.POINTER);
        BoolQueryBuilder queryBuilder = (BoolQueryBuilder) context.get(SqlContextConst.BOOLQUERY);

        if (word.toLowerCase().indexOf(">") != -1) {
            logger.debug("{} enter ge command...", word);
            String a = null, b = null;
            if (word.indexOf("=") != -1) {
                if (word.equals(">=")) {
                    a = words[pointer - 1];
                    b = words[pointer + 1];
                    queryBuilder.must(rangeQuery(a).gte(b));
                } else {
                    String[] _words = word.split(">=");
                    if (word.indexOf("=>") != -1) {
                        _words = word.split("=>");
                    }
                    if (_words.length == 1) {
                        if (word.startsWith(">=")) {
                            a = words[pointer - 1];
                            b = _words[0];
                        } else {
                            a = _words[0];
                            b = words[pointer + 1];
                            pointer++;
                        }
                    } else if (_words.length == 2) {
                        a = _words[0];
                        b = _words[1];
                    }
                    queryBuilder.must(rangeQuery(a).gte(b));
                }
            } else if ((pointer + 1) < words.length && words[pointer + 1].indexOf("=") != -1) {
                // > =
            } else if ((pointer + 1) < words.length && words[pointer + 1].indexOf("=") != -1) {
                // = <
            } else {
                if (word.equals(">")) {
                    a = words[pointer - 1];
                    b = words[pointer + 1];
                    queryBuilder.must(rangeQuery(a).gt(b));
                } else {
                    String[] _words = word.split(">");
                    if (_words.length == 1) {
                        if (word.startsWith(">")) {
                            a = words[pointer - 1];
                            b = _words[0];
                        } else {
                            a = _words[0];
                            b = words[pointer + 1];
                            pointer++;
                        }
                    } else if (_words.length == 2) {
                        a = _words[0];
                        b = _words[1];
                    }
                    queryBuilder.must(rangeQuery(a).gt(b));
                }
            }
            context.put(SqlContextConst.POINTER, ++pointer);
            if (pointer < words.length) {
                context.put(SqlContextConst.CURRENT_WOLD, words[pointer]);
            }
            return PROCESSING_COMPLETE;
        }
        return CONTINUE_PROCESSING;
    }
}
