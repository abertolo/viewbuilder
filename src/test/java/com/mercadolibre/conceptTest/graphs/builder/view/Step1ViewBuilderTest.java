package com.mercadolibre.conceptTest.graphs.builder.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadolibre.conceptTest.graphs.builder.model.attribute.pks.PksModelBuilder;
import com.mercadolibre.conceptTest.graphs.model.CategorySelectionModel;
import com.mercadolibre.conceptTest.graphs.model.FinderModel;
import com.mercadolibre.conceptTest.graphs.model.PksModel;
import com.mercadolibre.conceptTest.graphs.model.Step1Model;
import com.mercadolibre.conceptTest.template.Step1Template;
import com.mercadolibre.dto.category.AttributeValueType;
import com.mercadolibre.dto.category.CategoryAttribute;
import com.mercadolibre.dto.category.CategoryAttributeHierarchy;
import com.mercadolibre.flux.flow.graph.navigation.DataProxy;
import com.mercadolibre.flux.flow.graph.navigation.SimpleDataProxy;
import com.mercadolibre.kisc.viewbuilder.Component;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * Created by mforte on 1/12/18.
 */
public class Step1ViewBuilderTest {

    final Gson gson = new GsonBuilder().create();

    @Test
    public void testStep1() {
        Step1Model model = buildModel();
        final Component build = new Step1Template().build(model);

        System.out.println(gson.toJson(build));
    }

    private Step1Model buildModel() {
        return new Step1Model()
                .setFinderModel(getFinderModel())
                .setCategorySelectionModel(getCategorySelectionModel())
                .setPksModel(getPksModel());
    }

    private CategorySelectionModel getCategorySelectionModel() {
        final CategorySelectionModel categorySelectionModel = new CategorySelectionModel()
                .withCategoryId("MLA3530")
                .withAdultContent(false)
                .withShowCategorySelectionComponent(false)
                .withShowCategoryBreadcrumbComponent(false);
        categorySelectionModel.getHeaderModel().withTitle("CATEGORY_SELECTION_TITLE");
        categorySelectionModel.getFooterModel().withButtonConnection("SAME_STEP_CONNECTION");
        return categorySelectionModel;
    }

    private FinderModel getFinderModel() {
        final FinderModel finderModel = new FinderModel().setTitle("Iphone 6s").setRequired(true);
        finderModel.getHeaderModel().withTitle("FINDER_TITLE");
        finderModel.getFooterModel().withButtonConnection("SAME_STEP_CONNECTION");
        return finderModel;
    }

    private PksModel getPksModel() {
        final PksModel pksModel = new PksModel()
                .setPksAttributes(getListOfAttributes())
                .setDecimalSeparator(",");
        pksModel.getHeaderModel().withTitle(PksModelBuilder.HEADER_TITLE);
        pksModel.getFooterModel().withButtonText(PksModelBuilder.FOOTER_BTN_TEXT);
        return pksModel;
    }

    private List<DataProxy> getListOfAttributes() {
        SimpleDataProxy list = SimpleDataProxy.newListDataProxy();


        CategoryAttribute modelAttr = new CategoryAttribute()
                .setId("MODEL")
                .setName("Modelo")
                .setValueType(AttributeValueType.STRING)
                .setTags(Collections.singletonMap("product_pk", true))
                .setValueMaxLength(60)
                .setHierarchy(CategoryAttributeHierarchy.PARENT_PK)
                .setAttributeGroupId("MAIN")
                .setAttributeGroupName("Principales");

        DataProxy modelDp = new SimpleDataProxy(modelAttr.toMap());

        return list.addToList(modelDp).getListValue();
    }
}