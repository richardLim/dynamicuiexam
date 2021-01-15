package com.example.dynamicuiexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements HeadlinesFragment.OnHeadLineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState == null) {
                HeadlinesFragment headlinesFragment = new HeadlinesFragment();

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, headlinesFragment).commit();
            }
        }

   }

    @Override
    public void onHeadlineSelected(int position) {
        ArticleFragment articleFragment = (ArticleFragment)getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if (articleFragment == null) {

            articleFragment = new ArticleFragment();

            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION, position);
            articleFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, articleFragment)
                    .addToBackStack(null).commit();

        } else {
            articleFragment.updateArticleView(position);
        }
    }
}